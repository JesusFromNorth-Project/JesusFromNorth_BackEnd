package org.example.clinic_system.repository;

import org.example.clinic_system.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByPatientDni(String dni);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id_patient = ?1")
    List<Appointment> findByIdPatient(UUID id);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id_doctor = ?1")
    List<Appointment> findByIdDoctor(UUID id);
}
