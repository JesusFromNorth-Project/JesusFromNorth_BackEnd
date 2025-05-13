package org.example.clinic_system.service.Appointment;

import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    Appointment saveAppointment(AppointmentDTO appointment);
    Appointment findById(UUID id) throws NotFoundException;
    List<Appointment> findPatientById(UUID id) throws NotFoundException;
    List<Appointment> findDoctorById(UUID id) throws NotFoundException;
    List<Appointment> findAll(String dni);
    void deleteAppointment(UUID id) throws NotFoundException;
    Appointment update(UUID id, AppointmentDTO appointment) throws NotFoundException;
}
