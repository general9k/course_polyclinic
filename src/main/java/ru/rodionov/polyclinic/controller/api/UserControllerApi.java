package ru.rodionov.polyclinic.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rodionov.polyclinic.model.User;

import java.util.UUID;

@Controller("/api/v1/users")
public interface UserControllerApi {

    @RequestMapping(
            method = RequestMethod.GET
    )
    String getUsers();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{userId}"
    )
    User getUser(@PathVariable("userId") UUID userId);
}
