package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.SymptomControllerApi;
import ru.rodionov.polyclinic.service.facade.SymptomFacade;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SymptomController implements SymptomControllerApi {

    private final UserFacade userFacade;

    private final SymptomFacade symptomFacade;

    @Override
    public String getSymptoms(Model model) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("symptoms", symptomFacade.getSymptoms());
        return "/api/v1/symptoms";
    }

    @Override
    public String saveSymptom(Model model) {

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        return "/api/v1/symptoms/create";
    }

    @Override
    public String saveSymptom(String name) {
        symptomFacade.save(name);
        return "redirect:/api/v1/symptoms";
    }

    @Override
    public String deleteSymptom(UUID id) {
        symptomFacade.delete(id);
        return "redirect:/api/v1/symptoms";
    }
}
