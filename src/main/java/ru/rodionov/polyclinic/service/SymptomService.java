package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.Symptom;

import java.util.List;

public interface SymptomService {

    List<Symptom> getSymptoms();

    void save(String name);
}
