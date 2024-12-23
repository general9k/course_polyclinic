package ru.rodionov.polyclinic.service;

import ru.rodionov.polyclinic.model.User;

import java.util.List;
import java.util.UUID;

public interface DoctorService {

    List<User> getDoctors(String specialization, String lastName);

    User getDoctor(UUID id);

    List<String> getPositions();

    List<User> getDoctors();
}
