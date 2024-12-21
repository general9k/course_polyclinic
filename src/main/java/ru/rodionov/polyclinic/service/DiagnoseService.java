package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.Diagnose;

import java.util.List;
import java.util.UUID;

public interface DiagnoseService {

    List<Diagnose> getDiagnoses();

    void save(String name);

    void delete(UUID id);

    Diagnose getDiagnose(String id);
}
