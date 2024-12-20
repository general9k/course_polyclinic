package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.DoctorControllerApi;
import ru.rodionov.polyclinic.service.facade.DoctorFacade;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DoctorController implements DoctorControllerApi {

    private final DoctorFacade doctorFacade;

    private final UserFacade userFacade;

    @Override
    public String getDoctors(Model model, String specialization, String lastName) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("doctors", doctorFacade.getDoctors(specialization, lastName));
        model.addAttribute("positions", doctorFacade.getPositions());
        model.addAttribute("currentSpecialization", specialization);
        model.addAttribute("currentLastName", lastName);
        return "/api/v1/doctors";
    }

    @Override
    public String getDoctor(Model model, UUID id) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("doctor", doctorFacade.getDoctor(id));
        return "/api/v1/doctors/employee";
    }
}
