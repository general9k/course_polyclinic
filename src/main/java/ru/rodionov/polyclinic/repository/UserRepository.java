package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
