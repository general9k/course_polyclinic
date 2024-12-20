package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.UUID;

@RequestMapping("api/v1")
public interface DoctorControllerApi {
    @RequestMapping(
            value = "/doctors",
            method = RequestMethod.GET
    )
    String getDoctors(Model model);

    @RequestMapping(
            value = "/doctors/{id}",
            method = RequestMethod.GET
    )
    String getDoctor(Model model, @PathVariable UUID id) throws IOException;
}