package ru.rodionov.polyclinic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.enums.RoleEnum;
import ru.rodionov.polyclinic.repository.UserRepository;
import ru.rodionov.polyclinic.service.DoctorService;
import ru.rodionov.polyclinic.util.exception.ServerLogicException;
import ru.rodionov.polyclinic.util.exception.ServerLogicExceptionType;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final UserRepository repository;

    @Override
    public List<User> getDoctors() {
        return repository.findUserByAuthUser_Role(RoleEnum.MODERATOR.getCode().substring(5));
    }

    @Override
    public User getDoctor(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new ServerLogicException("Доктор с id = %s не найден".formatted(id), ServerLogicExceptionType.NOT_FOUND));
    }
}
