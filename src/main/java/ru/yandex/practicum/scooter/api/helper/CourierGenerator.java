package ru.yandex.practicum.scooter.api.helper;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.practicum.scooter.api.model.CreateCourierRequest;
import ru.yandex.practicum.scooter.api.model.CreateOrderRequest;

import java.util.List;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;

public class CourierGenerator {
    public static CreateCourierRequest getRandomCourier() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);

        return new CreateCourierRequest(login, password, firstName);
    }

    public static CreateCourierRequest getAlreadyCreatedCourier() {
        String login = "test123";
        String password = "test123";
        String firstName = "test123";

        return new CreateCourierRequest(login, password, firstName);
    }

    public static CreateOrderRequest getOrder() {
        String firstName = "Naruto";
        String lastName = "Uchiha";
        String address = "Konoha, 142 apt.";
        List<String> color = asList("BLACK");
        String phone = "+7 800 355 35 35";
        String comment = "Saske, come back to Konoha";
        int rentTime = 5;

        return new CreateOrderRequest(firstName, lastName, address, color, phone, comment, rentTime);
    }
}