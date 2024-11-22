package ru.rodionov.polyclinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.rodionov.polyclinic.model.AuthUser;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthUserMapper {

    AuthUser mapToAuthUser(CreateClientRequest createClientRequest);

}
