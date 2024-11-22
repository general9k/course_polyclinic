package ru.rodionov.polyclinic.util;

import ru.rodionov.polyclinic.model.enums.RoleEnum;
import ru.rodionov.polyclinic.util.interfaceEnum.UniversalEnumConverter;


public class RoleConverter extends UniversalEnumConverter<RoleEnum> {
    public RoleConverter() {
        super(RoleEnum.class);
    }
}
