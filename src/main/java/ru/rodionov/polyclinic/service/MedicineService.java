package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.Medicine;

import java.util.List;
import java.util.UUID;

public interface MedicineService {

    List<Medicine> getMedicines(String name);

    Medicine getMedicine(UUID id);

    void saveMedicine(Medicine medicine);

    void deleteMedicine(UUID id);
}
