package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.UserControllerApi;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.UpdateProfileRequest;
import ru.rodionov.polyclinic.service.facade.UserFacade;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserControllerApi {

    private final UserFacade userFacade;

    @Override
    public String getProfile(Model model) {

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        User user = userFacade.getByAuthId();

        model.addAttribute("user", user);

        return "/api/v1/profile";
    }

    @Override
    public String patchProfile(Model model) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("user", userFacade.getByAuthId());
        return "/api/v1/profile/update";
    }

    @Override
    public String patchProfile(UpdateProfileRequest request) {
        return "redirect:/api/v1/profile";
    }
}
