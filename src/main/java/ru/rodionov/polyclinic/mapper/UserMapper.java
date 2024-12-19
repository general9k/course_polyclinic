package ru.rodionov.polyclinic.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ObjectMapper.class)
public interface UserMapper {

    User mapToUser(CreateClientRequest createClientRequest);
}
