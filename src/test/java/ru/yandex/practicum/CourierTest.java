package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.scooter.api.client.CourierApiClient;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.*;

public class CourierTest {

    CreateCourierRequest createCourierRequest;
    CreateCourierRequest alreadyCreatedCourierRequest;
    CreateCourierRequest getOneParameterMissingCourier;
    LoginCourierRequest loginCourierRequest;
    LoginCourierRequest loginNotValidCourierRequest;

    CourierApiClient courierApiClient;


    @Before
    public void setUp() {
        courierApiClient = new CourierApiClient();

        createCourierRequest = getRandomCourier();
        alreadyCreatedCourierRequest = getAlreadyCreatedCourier();
        getOneParameterMissingCourier = getOneParameterMissingCourier();

        loginCourierRequest = new LoginCourierRequest(createCourierRequest.login, createCourierRequest.password);
        loginNotValidCourierRequest = new LoginCourierRequest(createCourierRequest.login);
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testCanRegisterAsValidCourier() {
        Boolean resultCreate =
                courierApiClient.createCourier(createCourierRequest)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .path("ok");

        assertTrue(resultCreate);

        Integer id =
                courierApiClient.loginCourier(loginCourierRequest)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("id");

        courierApiClient.deleteCourier(id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("id");
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testCanNotRegisterCourierTwice() {
        courierApiClient.createCourier(alreadyCreatedCourierRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CONFLICT)
                .extract()
                .path("code");
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testRegisterCourierOneParameterMissing() {
        courierApiClient.createCourier(getOneParameterMissingCourier)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .path("code");
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier/login")
    public void testCanCourierLogin() {
        courierApiClient.createCourier(createCourierRequest);

        Integer resultLogin =
                courierApiClient.loginCourier(loginCourierRequest)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("id");

        assertNotNull(resultLogin);
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier/login")
    public void testCanNotCourierLogin() {
        courierApiClient.createCourier(createCourierRequest);

        courierApiClient.loginCourier(loginNotValidCourierRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_GATEWAY_TIMEOUT);
    }
}
