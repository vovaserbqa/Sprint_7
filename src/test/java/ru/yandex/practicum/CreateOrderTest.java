package ru.yandex.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.scooter.api.helper.OrderGenerator;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseOrder {

    private final List<String> color;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] setColor() {
        return new Object[][]{
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GRAY")},
                {Arrays.asList("")},
        };
    }


    @Test
    @DisplayName("Check status code of /api/v1/orders")
    public void createOrders() {
        createOrderRequest = OrderGenerator.getRandomOrder(color);

        Integer resultMakeOrder =
                orderApi.createOrder(createOrderRequest)
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .path("track");

    }
}