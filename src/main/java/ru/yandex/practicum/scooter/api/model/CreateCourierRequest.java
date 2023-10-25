package ru.yandex.practicum.scooter.api.model;

public class CreateCourierRequest {
    // не могу поля сделать приватными так как тест потом не запускается
    public String login;
    public String password;
    public String firstName;


    public CreateCourierRequest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourierRequest(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourierRequest() {
    }
}
