package ru.rodionov.polyclinic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.repository.UserRepository;
import ru.rodionov.polyclinic.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User getUser(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
}
