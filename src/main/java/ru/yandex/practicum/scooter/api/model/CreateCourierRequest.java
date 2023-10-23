package ru.yandex.practicum.scooter.api.model;

public class CreateCourierRequest {
    public String login;
    public String password;
    public String firstName;


    public CreateCourierRequest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourierRequest() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }
}
