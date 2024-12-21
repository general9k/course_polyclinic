package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findUserByAuthUser_Role(String role);

    User findUserByIdAndAuthUser_Role(UUID id, String role);

    Optional<User> findByAuthUser_Id(UUID id);
}
