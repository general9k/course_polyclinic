package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.Medicine;

import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
}
