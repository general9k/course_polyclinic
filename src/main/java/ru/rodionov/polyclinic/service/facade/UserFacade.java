package ru.rodionov.polyclinic.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.mapper.UserMapper;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.dto.UserDTO;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;
import ru.rodionov.polyclinic.model.request.UpdateUserRequest;
import ru.rodionov.polyclinic.service.AuthService;
import ru.rodionov.polyclinic.service.UserService;
import ru.rodionov.polyclinic.service.WorkerService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {

    private final AuthService authService;

    private final WorkerService workerService;

    private final UserService userService;

    private final UserMapper userMapper;

    @Transactional
    public void saveUser(CreateClientRequest createClientRequest) throws IOException {
        authService.save(createClientRequest);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getWorkers() {
        return userMapper.mapToUserDTO(workerService.getWorkers());
    }

    @Transactional(readOnly = true)
    public UserDTO getWorker(UUID id) {
        return userMapper.mapToUserDTO(workerService.getWorkerById(id));
    }

    @Transactional
    public void updateWorker(UUID id, UpdateUserRequest request) {
        User worker = workerService.getWorkerById(id);
        userService.save(userMapper.updateUser(worker, request));
    }

    @Transactional
    public void deleteWorker(UUID id) {
        userService.delete(workerService.getWorkerById(id));
    }
}
