package ru.rodionov.polyclinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User mapToUser(CreateClientRequest createClientRequest);

}
