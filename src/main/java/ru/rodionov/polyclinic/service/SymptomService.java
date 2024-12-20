package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.Symptom;

import java.util.List;
import java.util.UUID;

public interface SymptomService {

    List<Symptom> getSymptoms();

    void save(String name);

    void delete(UUID id);
}
