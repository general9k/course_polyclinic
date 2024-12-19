package ru.rodionov.polyclinic.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.AuthControllerApi;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final UserFacade userFacade;

    @Override
    public String redirectOnLogin() {
        return "redirect:/login";
    }

    @Override
    public String getLogin(Model model, Boolean error) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        if (Boolean.TRUE.equals(error)) {
            return "login/error";
        }
        return "login";
    }

    @Override
    public String getRegistration(Model model) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        return "registration";
    }

    @Override
    @Transactional
    public String registration(CreateClientRequest createClientRequest) throws IOException {
        userFacade.saveUser(createClientRequest);
        return "redirect:/login";
    }

    @Override
    public String startPage(Model model) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        return "api/v1/index";
    }
}
