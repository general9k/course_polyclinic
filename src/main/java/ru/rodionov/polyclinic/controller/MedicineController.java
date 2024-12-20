package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.MedicineControllerApi;
import ru.rodionov.polyclinic.model.Medicine;
import ru.rodionov.polyclinic.service.facade.MedicineFacade;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MedicineController implements MedicineControllerApi {

    private final MedicineFacade medicineFacade;

    private final UserFacade userFacade;

    @Override
    public String getMedicines(Model model, String name) {

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("medicines", medicineFacade.getMedicines(name));
        model.addAttribute("currentName", name);

        return "api/v1/medicines";
    }

    @Override
    public String createMedicine(Model model) {

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        return "api/v1/medicines/create";
    }

    @Override
    public String createMedicine(Model model, Medicine medicine) {
        medicineFacade.saveMedicine(medicine);
        return "redirect:/api/v1/medicines";
    }

    @Override
    public String updateMedicine(Model model, UUID id) {

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());
        model.addAttribute("medicine", medicineFacade.getMedicine(id));

        return "api/v1/medicines/update";
    }

    @Override
    public String updateMedicine(UUID id, Medicine medicine) {

        Medicine old = medicineFacade.getMedicine(id);
        old.setName(medicine.getName() != null ? medicine.getName() : old.getName());
        old.setMeasuring(medicine.getMeasuring() != null ? medicine.getMeasuring() : old.getMeasuring());
        old.setMethod(medicine.getMethod() != null ? medicine.getMethod() : old.getMethod());
        old.setSideEffects(medicine.getSideEffects() != null ? medicine.getSideEffects() : old.getSideEffects());
        medicineFacade.saveMedicine(old);

        return "redirect:/api/v1/medicines";
    }


    @Override
    public String deleteMedicine(Model model, UUID id) {
        medicineFacade.deleteMedicine(id);
        return "redirect:/api/v1/medicines";
    }
}
