package ru.rodionov.polyclinic.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private UUID id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String position;

    private String description;

    private String education;

    private String email;

    private String phoneNumber;
}