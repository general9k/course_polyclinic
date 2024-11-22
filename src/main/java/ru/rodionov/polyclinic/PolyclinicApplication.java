package ru.rodionov.polyclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.rodionov.polyclinic.repository"})
@EntityScan(basePackages = {"ru.rodionov.polyclinic.model"})
public class PolyclinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolyclinicApplication.class, args);
    }

}
