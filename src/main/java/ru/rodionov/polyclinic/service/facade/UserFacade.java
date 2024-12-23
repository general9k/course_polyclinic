package ru.rodionov.polyclinic.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.rodionov.polyclinic.config.AuthUserDetails;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.model.request.CreateClientRequest;
import ru.rodionov.polyclinic.service.AuthService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {

    private final AuthService authService;

    @Transactional
    public void saveUser(CreateClientRequest createClientRequest, MultipartFile photo) throws IOException {
        authService.save(createClientRequest, photo);
    }

    @Transactional
    public void save(User user) {
        authService.save(user);
    }

    @Transactional(readOnly = true)
    public User getByAuthId() {
        return authService.getByAuthId(getCurrentUserId());
    }

    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return authService.getById(id);
    }


    public List<User> getUsers() {
        return authService.getUsers();
    }

    public UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated()
               && !(authentication.getPrincipal() instanceof String);
    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));
    }

    public boolean isUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_USER"));
    }

    public boolean isModerator() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_MODERATOR"));
    }
}
