package org.example.clinic_system.service.Patient;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.PatientResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.repository.PatientRepository;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.util.PatientProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService{

    private final PatientRepository patientRepository;
    private final AdminService adminService;

    @Override
    public Tuple<PatientResponseDTO, UUID> savePatient(UUID id_admin, PatientResponseDTO patientResponseDTO) throws NotFoundException {
        Admin admin = adminService.findById(id_admin);
        Patient patient = patientRepository.save(
                PatientProcesses.CreatePatient(patientResponseDTO,admin)
        );
        return new Tuple<>(PatientProcesses.createPatientResponseDTO(patient),patient.getId_patient());
    }

    @Override
    public PatientDTO getPatientDTOById(UUID id_patient) throws NotFoundException {
        Patient patient = patientRepository.findByIdPatient(id_patient)
                .orElseThrow( () -> new NotFoundException("No se encontro al paciente con el id: " + id_patient));
        return PatientProcesses.createPatientDTO(patient);
    }

    @Override
    public PatientDTO getPatientDTOByDni(String dni) throws NotFoundException {
        Patient patient =patientRepository.findByDni(dni)
                .orElseThrow( () -> new NotFoundException("No se encontro al paciente con el dni: " + dni));
        return PatientProcesses.createPatientDTO(patient);
    }

    @Override
    public Patient getPatientById(UUID id_patient) throws NotFoundException {
        return patientRepository.findByIdPatient(id_patient)
                .orElseThrow( () -> new NotFoundException("No se encontro al paciente con el id: " + id_patient));
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id_patient, PatientResponseDTO patientResponseDTO) throws NotFoundException {
        Patient patient = PatientProcesses.updatePatient(
                patientRepository.findByIdPatient(id_patient).orElseThrow( () -> new NotFoundException("No se encontro al paciente con el id: " + id_patient)),
                patientResponseDTO
        );
        patientRepository.save(patient);
        return PatientProcesses.createPatientResponseDTO(patient);
    }

    @Override
    public void deletePatient(UUID id_patient) throws NotFoundException {
        Patient patient = patientRepository.findByIdPatient(id_patient)
                .orElseThrow( () -> new NotFoundException("No se encontro al paciente con el id: " + id_patient));
        patient.setIs_deleted(true);
        patientRepository.save(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return PatientProcesses.CreatePatientResponseDTO(patientRepository.findAllPatients());
    }
}
