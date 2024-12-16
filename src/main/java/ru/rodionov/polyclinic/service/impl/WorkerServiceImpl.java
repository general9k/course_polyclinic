package ru.rodionov.polyclinic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.enums.RoleEnum;
import ru.rodionov.polyclinic.repository.UserRepository;
import ru.rodionov.polyclinic.service.WorkerService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkerServiceImpl implements WorkerService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getWorkers() {
        return userRepository.findUserByAuthUser_Role(RoleEnum.MODERATOR.getCode());
    }

    @Override
    @Transactional(readOnly = true)
    public User getWorkerById(UUID id) {
        return userRepository.findUserByIdAndAuthUser_Role(id, RoleEnum.MODERATOR.getCode());
    }
}
