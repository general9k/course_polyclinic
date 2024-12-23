package ru.rodionov.polyclinic.service.facade;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.Reception;
import ru.rodionov.polyclinic.service.ReceptionService;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceptionFacade {

    private final ReceptionService receptionService;

    public void save(Reception reception) {
        receptionService.save(reception);
    }

    public List<Reception> getReceptionsForDoctor(UUID id) {
        return receptionService.getReceptionsForDoctor(id);
    }

    public List<Reception> getReceptionsForUser(UUID id) {
        return receptionService.getReceptionsForUser(id);
    }

    public List<Reception> getReceptions() {
        return receptionService.getReceptions();
    }

    public void delete(UUID id) {
        receptionService.delete(id);
    }

    public Reception getById(UUID id) {
        return receptionService.getReception(id);
    }

}
