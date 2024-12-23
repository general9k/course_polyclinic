package ru.rodionov.polyclinic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.model.Reception;
import ru.rodionov.polyclinic.repository.ReceptionRepository;
import ru.rodionov.polyclinic.service.ReceptionService;
import ru.rodionov.polyclinic.util.exception.ServerLogicException;
import ru.rodionov.polyclinic.util.exception.ServerLogicExceptionType;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceptionServiceImpl implements ReceptionService {

    private final ReceptionRepository repository;

    @Override
    @Transactional
    public void save(Reception reception) {
        repository.save(reception);
    }

    @Override
    public List<Reception> getReceptionsForDoctor(UUID id) {
        return repository.findByWorker_Id(id);
    }

    @Override
    public List<Reception> getReceptionsForUser(UUID id) {
        return repository.findByPatient_Id(id);
    }

    @Override
    public List<Reception> getReceptions() {
        return repository.findAll();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Reception getReception(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServerLogicException("Прием с id %s не найден".formatted(id), ServerLogicExceptionType.NOT_FOUND));
    }
}
