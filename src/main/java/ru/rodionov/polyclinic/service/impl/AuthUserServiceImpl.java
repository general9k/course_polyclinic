package ru.rodionov.polyclinic.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.polyclinic.config.AuthUserDetails;
import ru.rodionov.polyclinic.mapper.AuthUserMapper;
import ru.rodionov.polyclinic.model.AuthUser;
import ru.rodionov.polyclinic.model.request.auth.AuthRequest;
import ru.rodionov.polyclinic.repository.AuthUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    private final AuthUserMapper authUserMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return authUserRepository.findByLogin(login)
                .map(AuthUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Авторизированный пользователь с таким %s не найден", login)));
    }

    @Transactional
    public void create(AuthRequest request) {
        authUserRepository.save(authUserMapper.mapToAuth(request));
    }

    public List<AuthUser> getUsers() {
        return authUserRepository.findAll();
    }
}
