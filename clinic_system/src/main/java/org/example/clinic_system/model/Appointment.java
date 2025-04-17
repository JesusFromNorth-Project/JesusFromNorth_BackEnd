package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_appointment")
    private UUID id_appointment;

    @Column(nullable = false)
    private LocalDateTime date_appointment;

    @Column(nullable = false)
    private LocalDateTime date_attention;

    @Column(nullable = false)
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_nurse",
            referencedColumnName = "id_nurse"
    )
    private Nurse nurse;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_doctor",
            referencedColumnName = "id_doctor"
    )
    private Doctor doctor;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_patient",
            referencedColumnName = "id_patient"
    )
    private Patient patient;
}
