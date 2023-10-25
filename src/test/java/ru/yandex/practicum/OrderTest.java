package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;

import static org.junit.Assert.assertNotNull;
import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.getOrder;

public class OrderTest {

    CreateOrderRequest createOrderRequest;
    CourierApiClient courierApiClient;

    @Before
    public void setUp() {
        courierApiClient = new CourierApiClient();

        createOrderRequest = getOrder();
    }

    @Test
    @DisplayName("Check status code of /api/v1/orders")
    public void testCanMakeOrders() {
        Integer resultMakeOrder =
                courierApiClient.makeOrder(createOrderRequest)
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .path("track");

        assertNotNull(resultMakeOrder);

        courierApiClient.deleteOrder(resultMakeOrder)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("id");
    }

    @Test
    @DisplayName("Check status code of /api/v1/orders")
    public void testGetOrders() {
        // let's fill the database
        Integer resultMakeOrder =
                courierApiClient.makeOrder(createOrderRequest)
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .path("track");

        courierApiClient.getOrders()
                .then()
                .statusCode(HttpStatus.SC_OK);

        courierApiClient.deleteOrder(resultMakeOrder)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("id");
    }
}
