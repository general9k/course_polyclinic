package ru.rodionov.polyclinic.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.dto.UserDTO;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;
import ru.rodionov.polyclinic.model.request.UpdateUserRequest;

import java.beans.PropertyDescriptor;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ObjectMapper.class)
public interface UserMapper {

    User mapToUser(CreateClientRequest createClientRequest);

    UserDTO mapToUserDTO(User user);

    List<UserDTO> mapToUserDTO(List<User> users);

    default User updateUser(User user, UpdateUserRequest request) {
        if (request == null) {
            return user;
        }

        BeanWrapper src = new BeanWrapperImpl(request);
        BeanWrapper trg = new BeanWrapperImpl(user);

        for (PropertyDescriptor descriptor : src.getPropertyDescriptors()) {
            Object value = src.getPropertyValue(descriptor.getName());
            if (value != null) {
                trg.setPropertyValue(descriptor.getName(), value);
            }
        }
        return user;
    }
}
