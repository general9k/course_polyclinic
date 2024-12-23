package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rodionov.polyclinic.model.request.CreateReceptionRequest;
import ru.rodionov.polyclinic.model.request.UpdateReceptionRequest;

import java.util.UUID;

@RequestMapping("api/v1")
public interface ReceptionControllerApi {
    @RequestMapping(
            value = "/doctors/{id}/reception",
            method = RequestMethod.GET
    )
    String getCreateReception(Model model, @PathVariable UUID id);

    @RequestMapping(
            value = "/doctors/{id}/reception",
            method = RequestMethod.POST
    )
    String getCreateReception(@PathVariable UUID id, @ModelAttribute CreateReceptionRequest request);

    @RequestMapping(
            value = "/receptions",
            method = RequestMethod.GET
    )
    String getReceptions(Model model);

    @RequestMapping(
            value = "/receptions/{id}/delete",
            method = RequestMethod.POST
    )
    String deleteReceptions(@PathVariable UUID id);

    @RequestMapping(
            value = "/receptions/{id}/edit",
            method = RequestMethod.GET
    )
    String updateReception(Model model, @PathVariable UUID id);

    @RequestMapping(
            value = "/receptions/{id}/edit",
            method = RequestMethod.POST
    )
    String updateReception(@PathVariable UUID id, UpdateReceptionRequest request);
}
