package org.example.clinic_system.repository;

import org.example.clinic_system.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

/**
 * The {@code PatientRepository} interface is used to define access methods for
 * data related to the {@code Patient} entity.
 * It allows operations such as inserting, updating, deleting, and querying
 * patients in the database.
 * This interface extends {@code JpaRepository}, providing automatic implementation of basic persistence methods without the need to define them explicitly.
 */
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByDni(String dni);
    void deleteByDni(String dni);
    Optional<Patient> findByDni(String dni);
}
