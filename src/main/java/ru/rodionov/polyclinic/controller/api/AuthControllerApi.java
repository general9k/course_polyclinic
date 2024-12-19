package ru.rodionov.polyclinic.controller.api;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;

import java.io.IOException;

public interface AuthControllerApi {
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    String redirectOnLogin();

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET)
    String getLogin(@RequestParam(required = false) Boolean error);

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.GET)
    String getRegistration();

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST
    )
    String registration(@ModelAttribute CreateClientRequest createClientRequest) throws IOException;
}
