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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Value("${appointment-size}")
    private int size;

    @Value("${appointment-doctor-size}")
    private int sizeDoctor;

    @Value("${appointment-patient-size}")
    private int sizePatient;

    @Override
    public Tuple<AppointmentResponseDTO, UUID> saveAppointment(
            UUID id_admin, UUID id_doctor, UUID id_patient, AppointmentResponseDTO responseDTO
    ) throws NotFoundException {
        Admin admin = adminService.findById(id_admin);
        Doctor doctor = doctorService.getDoctorById(id_doctor);
        Patient patient = patientService.getPatientById(id_patient);
        Appointment appointment = appointmentRepository.save(AppointmentProcesses.CreateAppointment(responseDTO,doctor,patient,admin));
        return new Tuple<>(
                AppointmentProcesses.CreateAppointmentResponseDTO(appointment),
                appointment.getId_appointment()
        );
    }

    @Override
    public AppointmentResponseDTO updateAppointment(UUID id_appointment, AppointmentResponseDTO appointmentResponseDTO) throws NotFoundException {
        Appointment appointment = AppointmentProcesses.UpdateAppointment(
                appointmentRepository.findByIdAppointment(id_appointment)
                        .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment) ),
                appointmentResponseDTO
        );
        appointmentRepository.save(appointment);
        return AppointmentProcesses.CreateAppointmentResponseDTO(appointment);
    }

    @Override
    public AppointmentDTO getAppointmentDTOById(UUID id_appointment) throws NotFoundException {
        return AppointmentProcesses.CreateAppointmentDTO(
                appointmentRepository.findByIdAppointment(id_appointment)
                        .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment))
        );
    }

    @Override
    public Appointment getAppointmentById(UUID id_appointment) throws NotFoundException {
        return appointmentRepository.findByIdAppointment(id_appointment)
                .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment));
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByIdPatient(UUID id_patient,int page) throws NotFoundException {
        return appointmentRepository.findByIdPatient(
                id_patient,
                PageRequest.of(page,sizePatient)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByIdDoctor(UUID id_doctor,int page) throws NotFoundException {
        return appointmentRepository.findByIdDoctor(
                id_doctor,
                PageRequest.of(page,sizeDoctor)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByCmpDoctor(String cmp,int page) throws NotFoundException {
        return appointmentRepository.findByCmpDoctor(
                cmp,
                PageRequest.of(page,sizeDoctor)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDniPatient(String dni,int page) throws NotFoundException {
        return appointmentRepository.findByDniPatient(
                dni,
                PageRequest.of(page,sizePatient)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public List<AppointmentDTO> getAllAppointments(int page) {
        return appointmentRepository.findAll(
                PageRequest.of(page,size)
        ).map(AppointmentProcesses::CreateAppointmentDTO).getContent();
    }

    @Override
    public void deleteAppointment(UUID id_appointment) throws NotFoundException {
        Appointment appointment = appointmentRepository.findByIdAppointment(id_appointment)
                .orElseThrow( () -> new NotFoundException("No se encontro la cita con el id: " + id_appointment));
        appointment.setIs_deleted(true);
        appointmentRepository.save(appointment);
    }

}
