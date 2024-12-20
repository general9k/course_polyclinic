package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    String getLogin(Model model, @RequestParam(required = false) Boolean error);

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.GET)
    String getRegistration(Model model);

    @RequestMapping(
            value = "/registration",
            method = RequestMethod.POST
    )
    String registration(@ModelAttribute CreateClientRequest createClientRequest,
                        @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException;

    @RequestMapping(
            value = "/api/v1/index",
            method = RequestMethod.GET
    )
    String startPage(Model model);

}
