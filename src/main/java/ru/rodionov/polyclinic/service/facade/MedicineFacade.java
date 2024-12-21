package ru.rodionov.polyclinic.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.Medicine;
import ru.rodionov.polyclinic.service.MedicineService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicineFacade {

    private final MedicineService medicineService;

    public List<Medicine> getMedicines(String name) {
        return medicineService.getMedicines(name);
    }

    public List<Medicine> getMedicines(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return ids.stream()
                .map(s -> getMedicine(UUID.fromString(s)))
                .toList();
    }

    public Medicine getMedicine(UUID id) {
        return medicineService.getMedicine(id);
    }

    public void saveMedicine(Medicine medicine) {
        medicineService.saveMedicine(medicine);
    }

    public void deleteMedicine(UUID id) {
        medicineService.deleteMedicine(id);
    }
}
