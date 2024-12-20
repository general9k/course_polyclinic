package ru.rodionov.polyclinic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.model.Symptom;
import ru.rodionov.polyclinic.repository.SymptomRepository;
import ru.rodionov.polyclinic.service.SymptomService;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SymptomServiceImpl implements SymptomService {

    private final SymptomRepository symptomRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Symptom> getSymptoms() {
        return symptomRepository.findAll();
    }

    @Override
    @Transactional
    public void save(String name) {
        symptomRepository.save(Symptom.builder()
                .name(name)
                .build());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        symptomRepository.deleteById(id);
    }
}
