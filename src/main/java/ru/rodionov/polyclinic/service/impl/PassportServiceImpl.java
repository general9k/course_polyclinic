package ru.rodionov.polyclinic.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.Passport;
import ru.rodionov.polyclinic.repository.PassportRepository;
import ru.rodionov.polyclinic.service.PassportService;

@Service
@AllArgsConstructor
@Slf4j
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }
}
