package ru.rodionov.polyclinic.util.converter;

import ru.rodionov.polyclinic.model.enums.RoleEnum;


public class RoleConverter extends UniversalEnumConverter<RoleEnum> {
    public RoleConverter() {
        super(RoleEnum.class);
    }
}
