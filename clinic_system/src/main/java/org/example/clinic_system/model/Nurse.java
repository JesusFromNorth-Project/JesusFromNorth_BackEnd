package org.example.clinic_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "nurse")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class Nurse extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_nurse")
    private UUID id_nurse;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id_user"
    )
    private User user;
}
