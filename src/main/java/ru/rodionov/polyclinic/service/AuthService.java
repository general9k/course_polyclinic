package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.request.CreateClientRequest;

public interface AuthService {

    void save(CreateClientRequest createClientRequest);
}
