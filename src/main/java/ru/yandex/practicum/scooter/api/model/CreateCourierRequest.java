package ru.yandex.practicum.scooter.api.model;

public class CreateCourierRequest {
    private String login;
    private String password;
    private String firstName;


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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
