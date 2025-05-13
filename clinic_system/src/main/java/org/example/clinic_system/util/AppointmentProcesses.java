package org.example.clinic_system.util;

import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
import org.example.clinic_system.model.Appointment;

public class AppointmentProcesses {
    public static Appointment CreateAppointment(AppointmentDTO appointmentDTO) {
        return Appointment.builder()
                .date_appointment(appointmentDTO.getDate_appointment())
                .date_attention(appointmentDTO.getDate_appointment())
                .description(appointmentDTO.getDescription())
                .admin(null)
                .doctor(appointmentDTO.getDoctor())
                .patient(appointmentDTO.getPatient())
                .is_deleted(appointmentDTO.getIs_deleted())
                .build();
    }

    public static Appointment UpdateAppointment(Appointment appointment, AppointmentDTO appointmentDTO) {
        if (appointmentDTO.getDate_appointment() != null)
            appointment.setDate_appointment(appointmentDTO.getDate_appointment());

        if (appointmentDTO.getDate_attention() != null)
            appointment.setDate_attention(appointmentDTO.getDate_attention());

        if (appointmentDTO.getDescription() != null)
            appointment.setDescription(appointmentDTO.getDescription());

        if (appointmentDTO.getDoctor() != null)
            appointment.setDoctor(appointmentDTO.getDoctor());

        if (appointmentDTO.getPatient() != null)
            appointment.setPatient(appointmentDTO.getPatient());

        if (appointmentDTO.getIs_deleted() != null)
            appointment.setIs_deleted(appointmentDTO.getIs_deleted());

        return appointment;
    }

}
