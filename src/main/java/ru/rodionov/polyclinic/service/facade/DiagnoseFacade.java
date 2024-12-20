package ru.rodionov.polyclinic.service.facade;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.Diagnose;
import ru.rodionov.polyclinic.service.DiagnoseService;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiagnoseFacade {

    private final DiagnoseService diagnoseService;

    public List<Diagnose> getDiagnoses() {
        return diagnoseService.getDiagnoses();
    }

    public void saveDiagnose(String name) {
        diagnoseService.save(name);
    }

    public void deleteDiagnose(UUID id) {
        diagnoseService.delete(id);
    }
}
