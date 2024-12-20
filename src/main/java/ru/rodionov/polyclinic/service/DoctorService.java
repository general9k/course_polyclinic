package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.User;

import java.util.List;
import java.util.UUID;

public interface DoctorService {

    List<User> getDoctors();

    User getDoctor(UUID id);

}
