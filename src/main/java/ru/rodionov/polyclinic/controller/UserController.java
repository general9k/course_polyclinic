package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import ru.rodionov.polyclinic.controller.api.UserControllerApi;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.service.impl.UserServiceImpl;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController implements UserControllerApi {

    private final UserServiceImpl userServiceImpl;

    @Override
    public String getUsers() {
        return "Привет";
    }

    @Override
    public User getUser(@PathVariable UUID userId) {
        return userServiceImpl.getUser(userId);
    }
}
