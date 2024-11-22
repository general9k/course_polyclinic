package ru.rodionov.polyclinic.model.request.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthUserRequest extends AuthRequest {

    private String login;

    private String password;

    private String role = "USER";
}