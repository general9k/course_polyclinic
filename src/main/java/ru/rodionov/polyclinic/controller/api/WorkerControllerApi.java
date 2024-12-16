package ru.rodionov.polyclinic.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.rodionov.polyclinic.model.request.UpdateUserRequest;

import java.util.UUID;

@RequestMapping(value = "/api/v1")
public interface WorkerControllerApi {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/index"
    )
    String getWorkersInIndex(Model model);

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/workers"
    )
    String getWorkers(Model model);

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/workers/{id}"
    )
    String getWorker(@PathVariable UUID id, Model model);

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/workers/{id}"
    )
    String updateWorker(@PathVariable UUID id, @RequestBody UpdateUserRequest request);

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/workers/{id}"
    )
    String deleteWorker(@PathVariable UUID id);
}
