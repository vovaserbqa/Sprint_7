package ru.yandex.practicum.scooter.api.client;

import io.restassured.response.Response;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;

import static ru.yandex.practicum.scooter.api.config.ConfigApp.BASE_URL;

public class CourierApiClient extends BaseApiClient {

    public Response createCourier(CreateCourierRequest createCourierRequest) {
        return getPostSpec()
                .body(createCourierRequest)
                .when()
                .post(BASE_URL + "/api/v1/courier");
    }

    public Response loginCourier(LoginCourierRequest loginCourierRequest) {
        return getPostSpec()
                .body(loginCourierRequest)
                .when()
                .post(BASE_URL + "/api/v1/courier/login");
    }

    public Response deleteCourier(int id) {
        return getPostSpec()
                .when()
                .delete(BASE_URL + "/api/v1/courier/" + id);
    }

    public Response makeOrder(CreateOrderRequest createOrderRequest) {
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