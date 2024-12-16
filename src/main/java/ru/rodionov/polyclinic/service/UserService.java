package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);

    User save(User user);

    void delete(User user);
}
