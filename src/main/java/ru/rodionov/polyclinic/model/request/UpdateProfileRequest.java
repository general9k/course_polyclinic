package ru.rodionov.polyclinic.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {

    private String lastName;          // Фамилия
    private String firstName;         // Имя
    private String patronymic;        // Отчество (необязательное поле)
    private String email;             // Email

    private String series;            // Серия паспорта
    private String number;            // Номер паспорта
    private String code;              // Код подразделения
    private String issueDate;         // Дата выдачи паспорта

    private String city;              // Город
    private String street;            // Улица
    private String house;             // Дом
    private String apartment;         // Квартира
}
