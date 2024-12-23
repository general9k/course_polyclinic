package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.Reception;

import java.util.List;
import java.util.UUID;

public interface ReceptionService {

    void save(Reception reception);

    List<Reception> getReceptionsForDoctor(UUID id);

    List<Reception> getReceptionsForUser(UUID id);

    List<Reception> getReceptions();

    void delete(UUID id);

    Reception getReception(UUID id);
}
