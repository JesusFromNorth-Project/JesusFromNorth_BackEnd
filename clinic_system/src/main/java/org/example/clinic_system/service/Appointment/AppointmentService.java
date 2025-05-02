package org.example.clinic_system.service.Appointment;

import org.example.clinic_system.model.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    Appointment findById(UUID id);
    List<Appointment> findByPatientDni(String dni);
    void delete(UUID id);
    Iterable<Appointment> findAll();
    Appointment update(Appointment appointment);
}
