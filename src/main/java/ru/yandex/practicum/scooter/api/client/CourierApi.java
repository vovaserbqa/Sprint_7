package ru.yandex.practicum.scooter.api.client;

import io.restassured.response.Response;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;

public class CourierApi extends BaseApi {

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
}