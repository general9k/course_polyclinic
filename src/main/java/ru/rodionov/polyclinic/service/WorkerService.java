package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.User;

import java.util.List;
import java.util.UUID;

public interface WorkerService {

    List<User> getWorkers();

    User getWorkerById(UUID id);
}
