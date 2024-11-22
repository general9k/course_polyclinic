package ru.rodionov.polyclinic.model.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AuthRequest {

    private String login;

    private String password;

    private String role;
}
