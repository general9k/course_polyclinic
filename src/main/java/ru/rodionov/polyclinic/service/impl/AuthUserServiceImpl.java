package ru.rodionov.polyclinic.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.mapper.AuthUserMapper;
import ru.rodionov.polyclinic.mapper.UserMapper;
import ru.rodionov.polyclinic.model.AuthUser;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;
import ru.rodionov.polyclinic.repository.AuthUserRepository;
import ru.rodionov.polyclinic.repository.UserRepository;
import ru.rodionov.polyclinic.service.AuthService;


@Service
@AllArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthUserRepository authUserRepository;
    private final UserRepository userRepository;

    private final AuthUserMapper authUserMapper;
    private final UserMapper userMapper;

    @Transactional
    public void save(CreateClientRequest createClientRequest) {
        AuthUser authUser = authUserMapper.mapToAuthUser(createClientRequest);
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));

        User user = userMapper.mapToUser(createClientRequest);

        var saved = authUserRepository.save(authUser);
        user.setAuthUser(saved);
        userRepository.save(user);
    }
}
