package ru.rodionov.polyclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rodionov.polyclinic.model.Reception;

import java.util.List;
import java.util.UUID;

public interface ReceptionRepository extends JpaRepository<Reception, UUID> {

    List<Reception> findByWorker_Id(UUID id);

    List<Reception> findByPatient_Id(UUID id);
}
