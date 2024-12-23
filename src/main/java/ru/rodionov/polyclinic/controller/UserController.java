package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.UserControllerApi;
import ru.rodionov.polyclinic.model.Address;
import ru.rodionov.polyclinic.model.Passport;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.UpdateProfileRequest;
import ru.rodionov.polyclinic.service.facade.AddressFacade;
import ru.rodionov.polyclinic.service.facade.PassportFacade;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserControllerApi {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final UserFacade userFacade;

    private final PassportFacade passportFacade;

    private final AddressFacade addressFacade;

    @Override
    public String getProfile(Model model) {

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        User user = userFacade.getByAuthId();

        model.addAttribute("user", user);

        if (user.getPassport() != null && user.getPassport().getIssueDate() != null) {
            String formattedIssueDate = user.getPassport().getIssueDate().format(formatter);
            model.addAttribute("formattedIssueDate", formattedIssueDate);
        }

        return "/api/v1/profile";
    }

    @Override
    public String patchProfile(Model model) {
        User user = userFacade.getByAuthId();

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        if (user.getPassport() != null && user.getPassport().getIssueDate() != null) {
            String formattedIssueDate = user.getPassport().getIssueDate().format(formatter);
            model.addAttribute("formattedIssueDate", formattedIssueDate);
        }

        model.addAttribute("user", user);
        return "/api/v1/profile/update";
    }

    @Override
    @Transactional
    public String patchProfile(UpdateProfileRequest request) {
        Passport passport = null;
        Address address = null;

        User user = userFacade.getByAuthId();
        user.setLastName(request.getLastName() != null && !request.getLastName().isEmpty() ? request.getLastName() : null);
        user.setFirstName(request.getFirstName() != null && !request.getFirstName().isEmpty() ? request.getFirstName() : null);
        user.setPatronymic(request.getPatronymic() != null && !request.getPatronymic().isEmpty() ? request.getPatronymic() : null);
        user.setEmail(request.getEmail() != null && !request.getEmail().isEmpty() ? request.getEmail() : null);


        if (request.getSeries() != null || request.getNumber() != null || request.getCode() != null || request.getIssueDate() != null) {
            LocalDateTime localDateTime = LocalDate.parse(request.getIssueDate(), formatter).atStartOfDay();

            OffsetDateTime issueDate = localDateTime.atOffset(ZoneOffset.UTC);

            passport = passportFacade.save(Passport.builder()
                    .series(request.getSeries())
                    .number(request.getNumber())
                    .code(request.getCode())
                    .issueDate(issueDate)
                    .build());
        }

        if (request.getCity() != null || request.getStreet() != null || request.getHouse() != null || request.getApartment() != null) {
            address = addressFacade.save(Address.builder()
                    .city(request.getCity())
                    .street(request.getStreet())
                    .house(request.getHouse())
                    .apartment(request.getApartment())
                    .build());
        }

        if (passport != null) {
            user.setPassport(passport);
        }
        if (address != null) {
            user.setAddress(address);
        }

        userFacade.save(user);

        return "redirect:/api/v1/profile";
    }
}
