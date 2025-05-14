package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.PatientResponseDTO;
import org.example.clinic_system.model.Patient;

public class PatientProcesses {



    public static Patient updatePatient(Patient patient, PatientResponseDTO patientResponseDTO) {
        PersonProcesses.UpdatePerson(patient,patientResponseDTO);
        if (patientResponseDTO.getBirthdate() != null) {
            patient.setBirthdate(patientResponseDTO.getBirthdate());
        }
        if (patientResponseDTO.getGender() != null) {
            patient.setGender(patientResponseDTO.getGender());
        }
        if (patientResponseDTO.getAge() != null) {
            patient.setAge(patientResponseDTO.getAge());
        }
        if (patientResponseDTO.getAntecedent() != null) {
            patient.setAntecedent(patientResponseDTO.getAntecedent());
        }
        return patient;
    }

}
