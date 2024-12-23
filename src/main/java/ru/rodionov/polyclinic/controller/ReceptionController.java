package ru.rodionov.polyclinic.controller;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.rodionov.polyclinic.controller.api.ReceptionControllerApi;
import ru.rodionov.polyclinic.model.Diagnose;
import ru.rodionov.polyclinic.model.Medicine;
import ru.rodionov.polyclinic.model.Reception;
import ru.rodionov.polyclinic.model.Symptom;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.CreateReceptionRequest;
import ru.rodionov.polyclinic.model.request.UpdateReceptionRequest;
import ru.rodionov.polyclinic.service.facade.DiagnoseFacade;
import ru.rodionov.polyclinic.service.facade.DoctorFacade;
import ru.rodionov.polyclinic.service.facade.MedicineFacade;
import ru.rodionov.polyclinic.service.facade.ReceptionFacade;
import ru.rodionov.polyclinic.service.facade.SymptomFacade;
import ru.rodionov.polyclinic.service.facade.UserFacade;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReceptionController implements ReceptionControllerApi {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private final UserFacade userFacade;

    private final ReceptionFacade receptionFacade;
    private final DiagnoseFacade diagnoseFacade;
    private final SymptomFacade symptomFacade;
    private final MedicineFacade medicineFacade;
    private final DoctorFacade doctorFacade;

    @Override
    public String getCreateReception(Model model, UUID id) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        model.addAttribute("symptoms", symptomFacade.getSymptoms());
        model.addAttribute("diagnoses", diagnoseFacade.getDiagnoses());
        model.addAttribute("medicines", medicineFacade.getMedicines(""));
        model.addAttribute("doctorId", id);

        return "/api/v1/doctors/reception";
    }

    @Override
    public String getCreateReception(UUID id, CreateReceptionRequest request) {
        User doctor = doctorFacade.getDoctor(id);
        User user = userFacade.getByAuthId();

        List<Diagnose> diagnoses = diagnoseFacade.getDiagnoses(request.getDiagnoses() != null ? List.copyOf(request.getDiagnoses()) : Collections.emptyList());
        List<Symptom> symptoms = symptomFacade.getSymptoms(request.getSymptoms() != null ? List.copyOf(request.getSymptoms()) : Collections.emptyList());
        List<Medicine> medicines = medicineFacade.getMedicines(request.getMedicines() != null ? List.copyOf(request.getMedicines()) : Collections.emptyList());

        LocalDateTime localDateTime = LocalDateTime.parse(request.getDateOfAppointment(), formatter);
        OffsetDateTime dateOfAppointment = localDateTime.atOffset(ZoneOffset.UTC);

        Reception reception = Reception.builder()
                .dateOfAppointment(dateOfAppointment)
                .wasCarriedOut(false)
                .prescription(request.getPrescription())
                .worker(doctor)
                .patient(user)
                .symptoms(!symptoms.isEmpty() ? Set.copyOf(symptoms) : Collections.emptySet())
                .diagnoses(!diagnoses.isEmpty() ? Set.copyOf(diagnoses) : Collections.emptySet())
                .medicines(!medicines.isEmpty() ? Set.copyOf(medicines) : Collections.emptySet())
                .build();

        receptionFacade.save(reception);

        return "redirect:/api/v1/doctors";
    }

    @Override
    public String getReceptions(Model model) {
        List<Reception> receptions = receptionFacade.getReceptions();

        boolean admin = userFacade.isAdmin();
        boolean user = userFacade.isUser();
        boolean moderator = userFacade.isModerator();

        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", admin);
        model.addAttribute("isModerator", moderator);
        model.addAttribute("isUser", user);

        if (moderator) {
            receptions = receptionFacade.getReceptionsForDoctor(userFacade.getByAuthId().getId());
        } else if (user) {
            receptions = receptionFacade.getReceptionsForUser(userFacade.getByAuthId().getId());
        }
        receptions = receptions.stream()
                .sorted(Comparator.comparing(Reception::getDateOfAppointment))
                .toList().reversed();

        model.addAttribute("receptions", receptions);

        return "/api/v1/receptions";
    }

    @Override
    public String deleteReceptions(UUID id) {
        receptionFacade.delete(id);
        return "redirect:/api/v1/receptions";
    }

    @Override
    public String updateReception(Model model, UUID id) {
        model.addAttribute("isAuthenticated", userFacade.isAuthenticated());
        model.addAttribute("isAdmin", userFacade.isAdmin());
        model.addAttribute("isModerator", userFacade.isModerator());

        Reception reception = receptionFacade.getById(id);
        String formattedDate = reception.getDateOfAppointment().format(formatter);
        model.addAttribute("formattedDateOfAppointment", formattedDate);

        model.addAttribute("reception", reception);
        model.addAttribute("symptoms", symptomFacade.getSymptoms());
        model.addAttribute("diagnoses", diagnoseFacade.getDiagnoses());
        model.addAttribute("medicines", medicineFacade.getMedicines(""));
        model.addAttribute("workers", doctorFacade.getDoctors());
        model.addAttribute("patients", userFacade.getUsers());

        return "/api/v1/receptions/update";
    }

    @Override
    @Transactional
    public String updateReception(UUID id, UpdateReceptionRequest request) {
        if (request.getWasCarriedOut() == null) {
            request.setWasCarriedOut(false);
        }

        Reception reception = receptionFacade.getById(id);
        User doctor = doctorFacade.getDoctor(request.getWorkerId());
        User user = userFacade.getById(request.getPatientId());

        List<Diagnose> diagnoses = diagnoseFacade.getDiagnoses(request.getDiagnoses() != null ? List.copyOf(request.getDiagnoses()) : Collections.emptyList());
        List<Symptom> symptoms = symptomFacade.getSymptoms(request.getSymptoms() != null ? List.copyOf(request.getSymptoms()) : Collections.emptyList());
        List<Medicine> medicines = medicineFacade.getMedicines(request.getMedicines() != null ? List.copyOf(request.getMedicines()) : Collections.emptyList());

        LocalDateTime localDateTime = LocalDateTime.parse(request.getDateOfAppointment(), formatter);
        OffsetDateTime dateOfAppointment = localDateTime.atOffset(ZoneOffset.UTC);

        if (request.getWasCarriedOut()) {
            reception.setDateInspection(OffsetDateTime.now());
        }

        reception.setDateOfAppointment(dateOfAppointment);
        reception.setWasCarriedOut(request.getWasCarriedOut());
        reception.setPrescription(request.getPrescription());
        reception.setWorker(doctor);
        reception.setPatient(user);
        reception.setSymptoms(new HashSet<>(symptoms));
        reception.setDiagnoses(new HashSet<>(diagnoses));
        reception.setMedicines(new HashSet<>(medicines));

        receptionFacade.save(reception);

        return "redirect:/api/v1/receptions";
    }
}
