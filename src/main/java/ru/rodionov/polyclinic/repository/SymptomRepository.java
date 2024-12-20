package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.Symptom;

import java.util.UUID;

public interface SymptomRepository extends JpaRepository<Symptom, UUID> {
}
