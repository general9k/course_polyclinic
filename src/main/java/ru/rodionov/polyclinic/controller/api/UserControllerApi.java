package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rodionov.polyclinic.model.request.UpdateProfileRequest;

@RequestMapping("api/v1")
public interface UserControllerApi {
    @RequestMapping(
            value = "/profile",
            method = RequestMethod.GET
    )
    String getProfile(Model model);

    @RequestMapping(
            value = "/profile/edit",
            method = RequestMethod.GET
    )
    String patchProfile(Model model);

    @RequestMapping(
            value = "/profile/edit",
            method = RequestMethod.POST
    )
    String patchProfile(UpdateProfileRequest request);

}
