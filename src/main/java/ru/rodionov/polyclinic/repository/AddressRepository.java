package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
