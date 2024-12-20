package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.Diagnose;

import java.util.UUID;

public interface DiagnoseRepository extends JpaRepository<Diagnose, UUID> {

}
