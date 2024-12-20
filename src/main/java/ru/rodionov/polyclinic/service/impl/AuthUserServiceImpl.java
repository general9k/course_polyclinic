package ru.rodionov.polyclinic.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.rodionov.polyclinic.mapper.AuthUserMapper;
import ru.rodionov.polyclinic.mapper.UserMapper;
import ru.rodionov.polyclinic.model.AuthUser;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;
import ru.rodionov.polyclinic.repository.AuthUserRepository;
import ru.rodionov.polyclinic.repository.UserRepository;
import ru.rodionov.polyclinic.service.AuthService;
import ru.rodionov.polyclinic.util.file.FileStorageService;

import java.io.IOException;


@Service
@AllArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthUserRepository authUserRepository;
    private final UserRepository userRepository;

    private final AuthUserMapper authUserMapper;
    private final UserMapper userMapper;
    private final FileStorageService fileStorageService;

    @Override
    @Transactional
    public void save(CreateClientRequest createClientRequest, MultipartFile photo) {

        try {
            String photoUrl = fileStorageService.saveFile(photo);
            createClientRequest.setPhotoUrl(photoUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AuthUser authUser = authUserMapper.mapToAuthUser(createClientRequest);
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));

        User user = userMapper.mapToUser(createClientRequest);
        var saved = authUserRepository.save(authUser);
        user.setAuthUser(saved);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
