package org.example.clinic_system.repository;

import org.example.clinic_system.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PatientRepository extends JpaRepository<Patient, UUID> {

    Optional<Patient> findByDni(String dni);

    boolean existsByDni(String dni);

}
