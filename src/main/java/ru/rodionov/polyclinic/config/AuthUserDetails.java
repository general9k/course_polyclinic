package ru.rodionov.polyclinic.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rodionov.polyclinic.model.AuthUser;

import java.util.Collection;
import java.util.stream.Stream;

@AllArgsConstructor
public class AuthUserDetails implements UserDetails {

    private AuthUser authUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(authUser.getRole())
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
