package ru.rodionov.polyclinic.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.model.Diagnose;
import ru.rodionov.polyclinic.repository.DiagnoseRepository;
import ru.rodionov.polyclinic.service.DiagnoseService;
import ru.rodionov.polyclinic.util.exception.ServerLogicException;
import ru.rodionov.polyclinic.util.exception.ServerLogicExceptionType;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DiagnoseServiceImpl implements DiagnoseService {

    private final DiagnoseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Diagnose> getDiagnoses() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void save(String name) {
        repository.save(Diagnose.builder()
                .name(name)
                .build());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Diagnose getDiagnose(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ServerLogicException("Диагноз с id %s не найден".formatted(id), ServerLogicExceptionType.NOT_FOUND));
    }
}
