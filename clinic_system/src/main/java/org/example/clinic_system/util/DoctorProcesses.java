package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.model.Doctor;


public class DoctorProcesses {
    public static Doctor CreateDoctor(DoctorDTO doctorDTO) {
        return Doctor.builder()
                .first_name(doctorDTO.getFirst_name())
                .last_name(doctorDTO.getLast_name())
                .email(doctorDTO.getEmail())
                .address(doctorDTO.getAddress())
                .phone(doctorDTO.getPhone())
                .landline_phone(doctorDTO.getLandline_phone())
                .dni(doctorDTO.getDni())
                .specialty(null)
                .cmp(doctorDTO.getCmp())
                .user(null)
                .admin(null)
                .is_deleted(doctorDTO.getIs_deleted())
                .build();
    }

    public static Doctor UpdateDoctor(Doctor doctor, DoctorDTO doctorDTO) {
        if (doctorDTO.getFirst_name() != null) {
            doctor.setFirst_name(doctorDTO.getFirst_name());
        }
        if (doctorDTO.getLast_name() != null) {
            doctor.setLast_name(doctorDTO.getLast_name());
        }
        if (doctorDTO.getEmail() != null) {
            doctor.setEmail(doctorDTO.getEmail());
        }
        if (doctorDTO.getAddress() != null) {
            doctor.setAddress(doctorDTO.getAddress());
        }
        if (doctorDTO.getPhone() != null) {
            doctor.setPhone(doctorDTO.getPhone());
        }
        if (doctorDTO.getLandline_phone() != null) {
            doctor.setLandline_phone(doctorDTO.getLandline_phone());
        }
        if (doctorDTO.getDni() != null) {
            doctor.setDni(doctorDTO.getDni());
        }
        if (doctorDTO.getCmp() != null) {
            doctor.setCmp(doctorDTO.getCmp());
        }
        if (doctorDTO.getIs_deleted() != null) {
            doctor.setIs_deleted(doctorDTO.getIs_deleted());
        }
        return doctor;
    }
}
