package org.example.clinic_system.dto;

import java.time.LocalDateTime;

import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {

    private LocalDateTime date_appointment;
    private LocalDateTime date_attention;
    private String description;
    private Admin admin;
    private Doctor doctor;
    private Patient patient;
    private Boolean is_deleted;
}
