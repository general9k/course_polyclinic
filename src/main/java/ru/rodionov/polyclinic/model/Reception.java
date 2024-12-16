package ru.rodionov.polyclinic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reception")
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "date_of_appointment", nullable = false)
    private OffsetDateTime dateOfAppointment;

    @CreatedDate
    @Column(name = "date_inspection")
    private OffsetDateTime dateInspection;

    @Column(name = "was_carried_out", nullable = false)
    private Boolean wasCarriedOut;

    @Column(name = "prescription")
    private String prescription;

    @ManyToOne
    @JoinColumn(name = "id_worker", referencedColumnName = "id", nullable = false)
    private User worker;

    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "id", nullable = false)
    private User patient;
}
