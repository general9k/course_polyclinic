package ru.rodionov.polyclinic.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.rodionov.polyclinic.model.AuthUser;
import ru.rodionov.polyclinic.model.User;
import ru.rodionov.polyclinic.repository.AuthUserRepository;
import ru.rodionov.polyclinic.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig {

    private final static String ADMIN_VALUE = "admin";
    public static final String PHONE_NUMBER = "123456789";

    private final UserDetailsService userDetailsService;
    private final AuthUserRepository authUserRepository;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (!authUserRepository.existsAuthUserByUsername(ADMIN_VALUE)) {
            AuthUser authUser = new AuthUser();
            authUser.setUsername(ADMIN_VALUE);
            authUser.setPassword("$2a$10$9sV6gtDcJOd.7HRZ/PuBMu73GVuD2w5B6U4LeH0d44V.8VIgU4IYu");
            authUser.setRole(ADMIN_VALUE.toUpperCase());

            User user = new User();
            user.setAuthUser(authUserRepository.save(authUser));
            user.setLastName(ADMIN_VALUE);
            user.setFirstName(ADMIN_VALUE);
            user.setPhoneNumber(PHONE_NUMBER);
            userRepository.save(user);
        }

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form ->
                        form
                                .loginPage("/login").permitAll()
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error=true").permitAll()
                                .defaultSuccessUrl("/api/v1/index", true))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/css/**", "/js/**", "/images/**",
                                "/", "/api/v1/index", "/api/v1/index/**",
                                "/registration", "/login", "/login/error", "/error", "/api/v1/doctors").permitAll()
                        .requestMatchers("/api/v1/**").authenticated())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
