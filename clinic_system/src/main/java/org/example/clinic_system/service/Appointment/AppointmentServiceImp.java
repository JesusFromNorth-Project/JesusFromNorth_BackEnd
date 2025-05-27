package org.example.clinic_system.service.Appointment;


import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
import org.example.clinic_system.dto.responseDTO.AppointmentResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.repository.AppointmentRepository;

import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Doctor.DoctorService;
import org.example.clinic_system.service.Patient.PatientService;
import org.example.clinic_system.util.AppointmentProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    private final AdminService adminService;

    private final DoctorService doctorService;

    private final PatientService patientService;

    @Override
    public Tuple<AppointmentResponseDTO, UUID> saveAppointment(UUID id_admin, UUID id_doctor, UUID id_patient, AppointmentResponseDTO responseDTO) throws NotFoundException {
        Admin admin = adminService.findById(id_admin);
        Doctor doctor = doctorService.getDoctorById(id_doctor);
        Patient patient = patientService.getPatientById(id_patient);
        Appointment appointment = AppointmentProcesses.CreateAppointment(responseDTO,doctor,patient,admin);
        return new Tuple<>(
                AppointmentProcesses.CreateAppointmentResponseDTO(appointment),
                appointment.getId_appointment()
        );
    }

    @Override
    public AppointmentResponseDTO updateAppointment(UUID id_appointment, AppointmentResponseDTO appointmentResponseDTO) throws NotFoundException {
        return null;
    }

    @Override
    public AppointmentDTO getAppointmentById(UUID id_appointment) throws NotFoundException {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByPatient(UUID id_patient) throws NotFoundException {
        return List.of();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDoctor(UUID id_doctor) throws NotFoundException {
        return List.of();
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return List.of();
    }

    @Override
    public AppointmentResponseDTO deleteAppointment(UUID id_appointment) throws NotFoundException {
        return null;
    }

}
