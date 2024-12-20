package ru.rodionov.polyclinic.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.model.Medicine;
import ru.rodionov.polyclinic.repository.MedicineRepository;
import ru.rodionov.polyclinic.service.MedicineService;
import ru.rodionov.polyclinic.util.exception.ServerLogicException;
import ru.rodionov.polyclinic.util.exception.ServerLogicExceptionType;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Medicine> getMedicines(String name) {
        List<Medicine> medicines = repository.findAll();

        if (name != null && !name.isEmpty()) {
            medicines = medicines.stream()
                    .filter(medicine -> medicine.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        return medicines;
    }

    @Override
    public Medicine getMedicine(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServerLogicException("Лекарство с id %s не найдено".formatted(id),
                        ServerLogicExceptionType.NOT_FOUND));
    }

    @Override
    @Transactional
    public void saveMedicine(Medicine medicine) {
        repository.save(medicine);
    }

    @Override
    @Transactional
    public void deleteMedicine(UUID id) {
        repository.deleteById(id);
    }
}
