package ru.yandex.practicum.scooter.api.helper;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;

public class CourierGenerator {
    @Step("Creating a request for a courier")
    public static CreateCourierRequest getRandomCourier() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);

        return new CreateCourierRequest(login, password, firstName);
    }

    @Step("Creating a request for a courier without login")
    public static CreateCourierRequest getRegisteringCourierWithoutLogin() {
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);

        return new CreateCourierRequest(password, firstName);
    }

    @Step("Creating a request for a courier without first name")
    public static CreateCourierRequest getRegisteringCourierWithoutFirstName() {
        String password = RandomStringUtils.randomAlphabetic(10);
        String login = RandomStringUtils.randomAlphabetic(10);

        return new CreateCourierRequest(password, login);
    }

    @Step("Creating a request for a courier without first password")
    public static CreateCourierRequest getRegisteringCourierWithoutPassword() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);

        return new CreateCourierRequest(login, firstName);
    }
}