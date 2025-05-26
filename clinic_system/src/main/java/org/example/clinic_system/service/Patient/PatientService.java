package org.example.clinic_system.service.Patient;

import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.PatientResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;


public interface PatientService {

    Tuple<PatientResponseDTO,UUID> savePatient(UUID id_admin,Patient patient) throws NotFoundException;

    PatientDTO getPatientById(UUID id_patient) throws NotFoundException;

    PatientDTO getPatientByDni(String dni) throws NotFoundException;

    PatientResponseDTO updatePatient(UUID id_patient, PatientResponseDTO patientResponseDTO) throws NotFoundException;

    //Hacer logica para devolver una lista de PacienteResponseDTO por el id del doctor

    //Hacer lista de total de pacientes inscriptos de la posta

}
