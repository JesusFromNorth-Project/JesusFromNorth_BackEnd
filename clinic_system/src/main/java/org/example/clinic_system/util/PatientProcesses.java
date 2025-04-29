package org.example.clinic_system.util;

import org.example.clinic_system.dto.PatientDTO;
import org.example.clinic_system.model.Patient;

public class PatientProcesses {
    public static Patient createPatient(PatientDTO patientDTO) {
        return Patient.builder()
                .first_name(patientDTO.getFirst_name())
                .last_name(patientDTO.getLast_name())
                .email(patientDTO.getEmail())
                .address(patientDTO.getAddress())
                .phone(patientDTO.getPhone())
                .landline_phone(patientDTO.getLandline_phone())
                .dni(patientDTO.getDni())
                .birthdate(patientDTO.getBirthdate())
                .gender(patientDTO.getGender())
                .age(patientDTO.getAge())
                .antecedent(patientDTO.getAntecedent())
                .admin(null)
                .build();
    }
}
