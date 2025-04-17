package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Patient extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_patient")
    protected UUID id_patient;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    protected Gender gender;

    @Column(nullable = false)
    protected Integer age;

    @Column(nullable = true)
    protected String antecedent;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_nurse",
            referencedColumnName = "id_nurse",
            nullable = false
    )
    private Nurse nurse;
}
