package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.enums.MedicalArea;

import java.util.UUID;

@Entity
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Doctor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_doctor")
    protected UUID id_doctor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected MedicalArea area;

    @Column(nullable = false)
    protected String cmp;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id_user"
    )
    protected User user;
}
