package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.getRegisteringCourierWithoutFirstName;
import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.getRegisteringCourierWithoutPassword;

public class CourierTest extends BaseCourier {


    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testCanRegisterAsValidCourier() {
        // testCanRegisterAsValidCourier
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
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testCanNotRegisterCourierTwice() {
        courierApiClient.createCourier(createCourierRequest);

        Integer id =
                courierApiClient.loginCourier(loginCourierRequest)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("id");

        // testCanNotRegisterCourierTwice
        courierApiClient.createCourier(createCourierRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CONFLICT)
                .extract()
                .path("code");

        courierApiClient.deleteCourier(id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testRegisteringCourierWithoutLogin() {
        // testRegisterCourierOneParameterMissing
        courierApiClient.createCourier(registeringCourierWithoutLogin)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .path("code");
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testRegisteringCourierWithoutPassword() {
        // testRegisteringCourierWithoutPassword
        courierApiClient.createCourier(getRegisteringCourierWithoutPassword())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .path("code");
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier")
    public void testRegisteringCourierWithoutFirstName() {
        // testRegisteringCourierWithoutFirstName
        courierApiClient.createCourier(getRegisteringCourierWithoutFirstName())
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

        // testCanCourierLogin
        Integer resultLogin =
                courierApiClient.loginCourier(loginCourierRequest)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("id");

        assertNotNull(resultLogin);

        courierApiClient.deleteCourier(resultLogin)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier/login")
    public void testCanNotLoginWithoutPassword() {
        courierApiClient.createCourier(createCourierRequest);

        Integer id =
                courierApiClient.loginCourier(loginCourierRequest)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("id");

        // testCanNotLoginWithoutPassword
        courierApiClient.loginCourier(loginWithoutPassword)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_GATEWAY_TIMEOUT);

        courierApiClient.deleteCourier(id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Check status code of /api/v1/courier/login")
    public void testCanNotLoginWithoutLogin() {
        courierApiClient.createCourier(createCourierRequest);

        Integer id =
                courierApiClient.loginCourier(loginCourierRequest)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("id");

        // testCanNotLoginWithoutLogin
        courierApiClient.loginCourier(loginWithoutLogin)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_GATEWAY_TIMEOUT);

        courierApiClient.deleteCourier(id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
