package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;

public class GetOrdersTest extends BaseOrder {


    @Test
    @DisplayName("Check status code of /api/v1/orders")
    public void testGetOrders() {
        orderApi.getOrders()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
