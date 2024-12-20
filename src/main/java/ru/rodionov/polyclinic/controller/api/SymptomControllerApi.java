package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/v1")
public interface SymptomControllerApi {
    @RequestMapping(
            value = "/symptoms",
            method = RequestMethod.GET
    )
    String getSymptoms(Model model);

    @RequestMapping(
            value = "/symptoms/create",
            method = RequestMethod.GET
    )
    String saveSymptom(Model model);

    @RequestMapping(
            value = "/symptoms/create",
            method = RequestMethod.POST
    )
    String saveSymptom(@RequestParam String name);
}
