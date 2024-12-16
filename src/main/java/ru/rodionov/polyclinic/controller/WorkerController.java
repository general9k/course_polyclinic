package ru.rodionov.polyclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.WorkerControllerApi;
import ru.rodionov.polyclinic.model.dto.UserDTO;
import ru.rodionov.polyclinic.model.request.UpdateUserRequest;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WorkerController implements WorkerControllerApi {

    private static final String CONST_URL_API = "api/v1/";

    private final UserFacade facade;

    @Override
    public String getWorkersInIndex(Model model) {
        List<UserDTO> workers = facade.getWorkers();
        log.info("Получены сотрудники: {}", workers);
        model.addAttribute("workers", workers);
        return CONST_URL_API + "index";
    }

    @Override
    public String getWorkers(Model model) {
        List<UserDTO> workers = facade.getWorkers();
        model.addAttribute("workers", workers);
        return CONST_URL_API + "workers";
    }

    @Override
    public String getWorker(UUID id, Model model) {
        UserDTO worker = facade.getWorker(id);
        model.addAttribute("worker", worker);
        log.info("Получен сотрудник: {}", worker);
        return CONST_URL_API + "worker";
    }

    @Override
    public String updateWorker(UUID id, UpdateUserRequest request) {
        facade.updateWorker(id, request);
        return "redirect:" + CONST_URL_API + "workers";
    }

    @Override
    public String deleteWorker(UUID id) {
        facade.deleteWorker(id);
        return "redirect:" + CONST_URL_API + "workers";
    }
}
