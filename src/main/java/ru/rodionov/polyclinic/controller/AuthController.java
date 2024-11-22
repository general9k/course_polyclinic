package ru.rodionov.polyclinic.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.rodionov.polyclinic.controller.api.AuthControllerApi;
import ru.rodionov.polyclinic.model.AuthUser;
import ru.rodionov.polyclinic.model.request.auth.CreateAuthAdminRequest;
import ru.rodionov.polyclinic.model.request.auth.CreateAuthModeratorRequest;
import ru.rodionov.polyclinic.model.request.auth.CreateAuthUserRequest;
import ru.rodionov.polyclinic.service.impl.AuthUserServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final AuthUserServiceImpl authUserServiceImpl;

    @Override
    public String createUser(CreateAuthUserRequest createAuthUserRequest) {
        authUserServiceImpl.create(createAuthUserRequest);
        return "User created";
    }

    @Override
    public String createAdmin(CreateAuthAdminRequest createAuthAdminRequest) {
        authUserServiceImpl.create(createAuthAdminRequest);
        return "Admin created";
    }

    @Override
    public String createModerator(CreateAuthModeratorRequest createAuthModeratorRequest) {
        authUserServiceImpl.create(createAuthModeratorRequest);
        return "Moderator created";
    }

    @Override
    public ModelAndView getAllAuthUser() {
        List<AuthUser> users = authUserServiceImpl.getUsers();
        ModelAndView modelAndView = new ModelAndView("all");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
