package org.example.clinic_system.util;

import org.example.clinic_system.dto.PatientDTO;
import org.example.clinic_system.handler.NotFoundException;
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

    public static Patient updatePatient(Patient patient, PatientDTO patientDTO) {
        if (patientDTO.getFirst_name() != null) {
            patient.setFirst_name(patientDTO.getFirst_name());
        }
        if (patientDTO.getLast_name() != null) {
            patient.setLast_name(patientDTO.getLast_name());
        }
        if (patientDTO.getEmail() != null) {
            patient.setEmail(patientDTO.getEmail());
        }
        if (patientDTO.getAddress() != null) {
            patient.setAddress(patientDTO.getAddress());
        }
        if (patientDTO.getPhone() != null) {
            patient.setPhone(patientDTO.getPhone());
        }
        if (patientDTO.getLandline_phone() != null) {
            patient.setLandline_phone(patientDTO.getLandline_phone());
        }
        if (patientDTO.getDni() != null) {
            patient.setDni(patientDTO.getDni());
        }
        if (patientDTO.getBirthdate() != null) {
            patient.setBirthdate(patientDTO.getBirthdate());
        }
        if (patientDTO.getGender() != null) {
            patient.setGender(patientDTO.getGender());
        }
        if (patientDTO.getAge() != null) {
            patient.setAge(patientDTO.getAge());
        }
        if (patientDTO.getAntecedent() != null) {
            patient.setAntecedent(patientDTO.getAntecedent());
        }
        return patient;
    }

}
