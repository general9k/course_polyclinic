package ru.rodionov.polyclinic;

import org.springframework.boot.SpringApplication;

public class TestPolyclinicApplication {

    public static void main(String[] args) {
        SpringApplication.from(PolyclinicApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
