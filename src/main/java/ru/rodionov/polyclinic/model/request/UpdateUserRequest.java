package ru.rodionov.polyclinic.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserRequest {

    private String lastName;

    private String firstName;

    private String patronymic;

    private String phoneNumber;

    private String medicalNumber;
}
