package ru.rodionov.polyclinic.service;

import org.springframework.web.multipart.MultipartFile;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;

import java.io.IOException;
import java.util.UUID;

public interface AuthService {

    void save(CreateClientRequest createClientRequest, MultipartFile photo) throws IOException;

    void save(User user);

    User getByAuthId(UUID id);
}
