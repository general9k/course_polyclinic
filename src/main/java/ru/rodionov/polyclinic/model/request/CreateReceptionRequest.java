package ru.rodionov.polyclinic.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateReceptionRequest {

    private String dateOfAppointment;

    private String prescription;

    private Set<String> symptoms;

    private Set<String> diagnoses;

    private Set<String> medicines;
}