package ru.rodionov.polyclinic.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UpdateReceptionRequest {

    private String dateOfAppointment;

    private String prescription;

    private Set<String> symptoms;

    private Set<String> diagnoses;

    private Set<String> medicines;

    private UUID patientId;

    private UUID workerId;

    private Boolean wasCarriedOut;

}
