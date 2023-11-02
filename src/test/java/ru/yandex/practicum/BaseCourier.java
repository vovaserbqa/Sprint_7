package ru.yandex.practicum;

import org.junit.Before;
import ru.yandex.practicum.scooter.api.client.CourierApi;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.LoginCourierRequest;

import static ru.yandex.practicum.scooter.api.helper.CourierGenerator.*;

public class BaseCourier {

    protected CreateCourierRequest createCourierRequest;
    protected CreateCourierRequest registeringCourierWithoutLogin;
    protected CreateCourierRequest registeringCourierWithoutPassword;
    protected CreateCourierRequest registeringCourierWithoutFirstName;
    protected LoginCourierRequest loginCourierRequest;
    protected LoginCourierRequest loginWithoutPassword;
    protected LoginCourierRequest loginWithoutLogin;
    protected CourierApi courierApiClient;


    @Before
    public void setUp() {
        courierApiClient = new CourierApi();

        createCourierRequest = getRandomCourier();
        registeringCourierWithoutLogin = getRegisteringCourierWithoutLogin();
        registeringCourierWithoutPassword = getRegisteringCourierWithoutPassword();
        registeringCourierWithoutFirstName = getRegisteringCourierWithoutFirstName();

        loginCourierRequest = new LoginCourierRequest(createCourierRequest.getLogin(), createCourierRequest.getPassword());
        loginWithoutPassword = new LoginCourierRequest(createCourierRequest.getLogin());
        loginWithoutLogin = new LoginCourierRequest(createCourierRequest.getPassword());
    }
}
