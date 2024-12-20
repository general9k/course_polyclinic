package ru.rodionov.polyclinic.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.service.DoctorService;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorFacade {

    private static final String URL = "/images/doctor";

    private final DoctorService doctorService;

    public List<User> getDoctors() {
        return doctorService.getDoctors();
    }

    public User getDoctor(UUID id) {
        return doctorService.getDoctor(id);
    }

}
