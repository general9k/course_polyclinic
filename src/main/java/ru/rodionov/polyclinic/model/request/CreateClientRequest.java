package ru.rodionov.polyclinic.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientRequest {

    private String username;
    private String password;
    private String role;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String position;
    private String description;
    private String education;
    private String email;
    private String phoneNumber;
    private String medicalNumber;
    private String photoUrl;

    public CreateClientRequest(String username, String password, String role, String lastName, String firstName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.role = role != null ? role : "USER";
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }
}
