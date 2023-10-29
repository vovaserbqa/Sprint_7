package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;

import static org.junit.Assert.assertNotNull;
import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.getOrder;

@RunWith(Parameterized.class)
public class OrderTest {

    protected CreateOrderRequest createOrderRequest;
    protected CourierApiClient courierApiClient;
    String colorBlack;
    String colorGrey;
    String colorBlackNadBlack;
    String noColor;

    @Before
    public void setUp() {
        courierApiClient = new CourierApiClient();
    }

    public OrderTest(String colorBlack, String colorGrey, String colorBlackNadBlack, String noColor) {
        this.colorBlack = colorBlack;
        this.colorGrey = colorGrey;
        this.colorBlackNadBlack = colorBlackNadBlack;
        this.noColor = noColor;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK GREY"},
                {""},
        };
    }

    @Test
    @DisplayName("Check status code of /api/v1/orders")
    public void testCanMakeOrders() {
        createOrderRequest = getOrder(colorBlack, colorGrey, colorBlackNadBlack, noColor);

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
        courierApiClient.getOrders()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
