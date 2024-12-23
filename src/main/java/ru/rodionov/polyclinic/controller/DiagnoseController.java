package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.DiagnoseControllerApi;
import ru.rodionov.polyclinic.service.facade.DiagnoseFacade;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DiagnoseController implements DiagnoseControllerApi {

    private final DiagnoseFacade diagnoseFacade;

    private final UserFacade userFacade;

    @Override
    public String getDiagnoses(Model model) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("diagnoses", diagnoseFacade.getDiagnoses());

        return "/api/v1/diagnosis";
    }

    @Override
    public String saveDiagnose(Model model) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("isAdmin", userFacade.isAdmin());

        return "/api/v1/diagnosis/create";
    }

    @Override
    public String saveDiagnose(String name) {
        diagnoseFacade.saveDiagnose(name);
        return "redirect:/api/v1/diagnosis";
    }

    @Override
    public String deleteDiagnose(UUID id) {
        diagnoseFacade.deleteDiagnose(id);
        return "redirect:/api/v1/diagnosis";
    }
}
