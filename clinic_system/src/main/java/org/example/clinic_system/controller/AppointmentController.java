package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AppointmentResponseDTO;
import org.example.clinic_system.dto.responseDTO.RegisterAppointment;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.service.Appointment.AppointmentService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping(value = "appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/")
    public ResponseEntity<?> addAppointment(@RequestBody RegisterAppointment registerAppointment) throws NotFoundException {

        Tuple <AppointmentResponseDTO, UUID> response = appointmentService.saveAppointment(
                registerAppointment.getId_admin(),
                registerAppointment.getId_doctor(),
                registerAppointment.getId_patient(),
                registerAppointment.getResponseDTO()
        );

        URI location = UriGeneric.CreateUri("/{id_appointment}",response.getSecond());

        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Se creo la cita correctamente")
                .data(response.getFirst())
                .build();

        return ResponseEntity.created(location).body(successMessage);
    }

}
