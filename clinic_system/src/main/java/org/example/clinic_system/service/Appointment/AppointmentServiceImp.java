package org.example.clinic_system.service.Appointment;

import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImp implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImp(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findById(UUID id) {
        return appointmentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cita con "));
    }

    @Override
    public List<Appointment> findByPatientDni(String dni) {
        List<Appointment> appointments = appointmentRepository.findByPatientDni(dni);

        if (appointments.isEmpty()) {
            throw new RuntimeException("No se encontraron citas para el paciente con DNI " + dni);
        }

        return appointments;

    }


    @Override
    public void delete(UUID id) {
        if(!appointmentRepository.existsById(id)){
            throw new RuntimeException("Cita con ID " + id + " no encontrada, no se puede eliminar");
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public Iterable<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment update(Appointment appointment) {
        if (!appointmentRepository.existsById(appointment.getId_appointment())) {
            throw new RuntimeException("Cita con ID " + appointment.getId_appointment() + " no encontrada, no se puede actualizar");
        }
        return appointmentRepository.save(appointment);
    }
}
