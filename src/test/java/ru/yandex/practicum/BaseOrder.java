package ru.yandex.practicum;

import org.junit.Before;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;

import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.getOrder;

public class BaseOrder {

    protected CreateOrderRequest createOrderRequest;
    protected CourierApiClient courierApiClient;

    @Before
    public void setUp() {
        courierApiClient = new CourierApiClient();
        createOrderRequest = getOrder();
    }

}
