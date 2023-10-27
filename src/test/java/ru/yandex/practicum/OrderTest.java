package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class OrderTest extends BaseOrder {


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
                .statusCode(HttpStatus.SC_OK);
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
                .statusCode(HttpStatus.SC_OK);
    }
}
