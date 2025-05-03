package org.example.clinic_system.service.Appointment;

import org.example.clinic_system.dto.AppointmentDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.repository.AppointmentRepository;
import org.example.clinic_system.util.AppointmentProcesses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImp implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImp(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment saveAppointment(AppointmentDTO appointmentDTO)  {
        Appointment appointment = AppointmentProcesses.CreateAppointment(appointmentDTO);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findById(UUID id) throws NotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(!appointmentOptional.isPresent() || appointmentOptional.get().getIs_deleted()){
            throw new NotFoundException("Appointment not found or deleted");
        }
        return appointmentOptional.get();
    }

    @Override
    public List<Appointment> findPatientById(UUID id) {
        List<Appointment> appointmentOptional = appointmentRepository.findByIdPatient(id);
        return appointmentOptional.stream()
                .filter(appointment -> !appointment.getIs_deleted())
                .toList();
    }

    @Override
    public List<Appointment> findDoctorById(UUID id) {
        List<Appointment> appointmentOptional = appointmentRepository.findByIdDoctor(id);
        return appointmentOptional.stream()
                .filter(appointment -> !appointment.getIs_deleted())
                .toList();
    }

    @Override
    public List<Appointment> findAll(String dni) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> !appointment.getIs_deleted())
                .toList();
    }

    @Override
    public void deleteAppointment(UUID id) throws NotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(!appointmentOptional.isPresent() || appointmentOptional.get().getIs_deleted()){
            throw new NotFoundException("Appointment not found or deleted");
        }
        Appointment appointment = appointmentOptional.get();
        appointment.setIs_deleted(true);
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(UUID id, AppointmentDTO appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(!appointmentOptional.isPresent() || appointmentOptional.get().getIs_deleted()){
            new NotFoundException("Appointment not found or deleted");
        }

        Appointment appointmentUpdate = AppointmentProcesses.UpdateAppointment(
                appointmentOptional.get(),
                appointment
        );

        return appointmentRepository.save(appointmentUpdate);
    }

}
