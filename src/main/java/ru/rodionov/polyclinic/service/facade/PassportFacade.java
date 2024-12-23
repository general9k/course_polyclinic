package ru.rodionov.polyclinic.service.facade;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.Passport;
import ru.rodionov.polyclinic.service.PassportService;

@Service
@Slf4j
@RequiredArgsConstructor
public class PassportFacade {

    private final PassportService passportService;

    public Passport save(Passport passport) {
        return passportService.save(passport);
    }
}
