package ru.rodionov.polyclinic.service.facade;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.Symptom;
import ru.rodionov.polyclinic.service.SymptomService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SymptomFacade {

    private final SymptomService symptomService;

    public List<Symptom> getSymptoms() {
        return symptomService.getSymptoms();
    }

    public List<Symptom> getSymptoms(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return symptomService.getSymptoms(ids);
    }

    public void save(String name) {
        symptomService.save(name);
    }

    public void delete(UUID id) {
        symptomService.delete(id);
    }
}
