package ru.rodionov.polyclinic.controller.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.rodionov.polyclinic.model.request.auth.CreateAuthAdminRequest;
import ru.rodionov.polyclinic.model.request.auth.CreateAuthModeratorRequest;
import ru.rodionov.polyclinic.model.request.auth.CreateAuthUserRequest;

@RequestMapping("/auth")
public interface AuthControllerApi {

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST
    )
    String createUser(@RequestBody CreateAuthUserRequest createAuthUserRequest);

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.POST
    )
    String createAdmin(@RequestBody CreateAuthAdminRequest createAuthAdminRequest);

    @RequestMapping(
            value = "/moderator",
            method = RequestMethod.POST
    )
    String createModerator(@RequestBody CreateAuthModeratorRequest createAuthModeratorRequest);

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET
    )
    ModelAndView getAllAuthUser();
}
