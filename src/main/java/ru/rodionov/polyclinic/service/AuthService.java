package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.request.CreateClientRequest;

import java.io.IOException;

public interface AuthService {

    void save(CreateClientRequest createClientRequest) throws IOException;
}
