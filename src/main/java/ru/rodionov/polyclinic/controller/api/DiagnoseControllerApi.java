package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/v1")
public interface DiagnoseControllerApi {
    @RequestMapping(
            value = "/diagnosis",
            method = RequestMethod.GET
    )
    String getDiagnoses(Model model);

    @RequestMapping(
            value = "/diagnosis/create",
            method = RequestMethod.GET
    )
    String saveDiagnose(Model model);

    @RequestMapping(
            value = "/diagnosis/create",
            method = RequestMethod.POST
    )
    String saveDiagnose(@RequestParam String name);
}
