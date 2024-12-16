package ru.rodionov.polyclinic.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.controller.api.AuthControllerApi;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final UserFacade facade;

    @Override
    public String getLogin(Boolean error) {
        if (Boolean.TRUE.equals(error)) {
            return "login/error";
        }
        return "login";
    }

    @Override
    public String getRegistration() {
        return "registration";
    }

    @Override
    @Transactional
    public String registration(CreateClientRequest createClientRequest) throws IOException {
        facade.saveUser(createClientRequest);
        return "redirect:/login";
    }
}
