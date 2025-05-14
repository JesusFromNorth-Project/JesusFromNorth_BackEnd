package org.example.clinic_system.repository;

import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByCmp(String cpf);
    Optional<Doctor> findByDni(String dni);
    @Query("SELECT d FROM Doctor d WHERE d.user.id_user=?1")
    Optional<Doctor> findByUser(UUID id_user);
}
