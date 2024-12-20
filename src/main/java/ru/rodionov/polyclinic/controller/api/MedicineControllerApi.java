package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rodionov.polyclinic.model.Medicine;

import java.util.UUID;

@RequestMapping("api/v1")
public interface MedicineControllerApi {
    @RequestMapping(
            value = "/medicines",
            method = RequestMethod.GET
    )
    String getMedicines(Model model,
                        @RequestParam(value = "name", required = false) String name);


    @RequestMapping(
            value = "/medicines/create",
            method = RequestMethod.GET
    )
    String createMedicine(Model model);

    @RequestMapping(
            value = "/medicines/create",
            method = RequestMethod.POST
    )
    String createMedicine(Model model, Medicine medicine);

    @RequestMapping(
            value = "/medicines/{id}/edit",
            method = RequestMethod.GET
    )
    String updateMedicine(Model model, @PathVariable UUID id);

    @RequestMapping(
            value = "/medicines/{id}/edit",
            method = RequestMethod.POST
    )
    String updateMedicine(@PathVariable UUID id, Medicine medicine);

    @RequestMapping(
            value = "/medicines/{id}/delete",
            method = RequestMethod.POST
    )
    String deleteMedicine(Model model, @PathVariable UUID id);
}
