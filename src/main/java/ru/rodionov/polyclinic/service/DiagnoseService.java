package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.Diagnose;

import java.util.List;

public interface DiagnoseService {

    List<Diagnose> getDiagnoses();

    void save(String name);
}
