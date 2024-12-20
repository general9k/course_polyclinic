package ru.rodionov.polyclinic.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.service.DoctorService;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorFacade {

    private final DoctorService doctorService;

    public List<User> getDoctors(String specialization, String lastName) {
        return doctorService.getDoctors(specialization, lastName);
    }

    public User getDoctor(UUID id) {
        return doctorService.getDoctor(id);
    }

    public List<String> getPositions() {
        return doctorService.getPositions();
    }
}
