package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.Passport;

import java.util.UUID;

public interface PassportRepository extends JpaRepository<Passport, UUID> {
}
