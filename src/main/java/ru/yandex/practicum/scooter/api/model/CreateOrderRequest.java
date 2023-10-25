package ru.yandex.practicum.scooter.api.model;

import java.util.List;

public class CreateOrderRequest {
    private String firstName;
    private String lastName;
    private String address;
    private List<String> color;
    private String phone;
    private String comment;
    private int rentTime;
    private String deliveryDate;
    private int metroStation;

    public CreateOrderRequest() {

    }

    public CreateOrderRequest(String firstName, String lastName, String address,
                              List<String> color, String phone, String comment, int rentTime, String deliveryDate, int metroStation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.color = color;
        this.phone = phone;
        this.comment = comment;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.metroStation = metroStation;
    }


}