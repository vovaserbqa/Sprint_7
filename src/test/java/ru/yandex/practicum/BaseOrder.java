package ru.yandex.practicum;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import ru.yandex.practicum.scooter.api.client.OrderApi;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;

public class BaseOrder {

    Integer resultMakeOrder;
    CreateOrderRequest createOrderRequest;
    OrderApi orderApi;

    @Before
    public void setUp() {
        orderApi = new OrderApi();
    }

    @After
    public void tearDown() {
        orderApi.deleteOrder(resultMakeOrder)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
