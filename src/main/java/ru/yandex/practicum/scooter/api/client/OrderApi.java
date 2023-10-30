package ru.yandex.practicum.scooter.api.client;

import io.restassured.response.Response;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;


public class OrderApi extends BaseApi {
    public Response createOrder(CreateOrderRequest createOrderRequest) {
        return getPostSpec()
                .body(createOrderRequest)
                .when()
                .post(BASE_URL + "/api/v1/orders");
    }

    public Response deleteOrder(int id) {
        return getPostSpec()
                .when()
                .put(BASE_URL + "/api/v1/orders/finish/" + id);
    }

    public Response getOrders() {
        return getPostSpec()
                .when()
                .get(BASE_URL + "/api/v1/orders");
    }
}
