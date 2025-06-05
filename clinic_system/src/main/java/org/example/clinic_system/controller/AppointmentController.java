package org.example.clinic_system.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AppointmentResponseDTO;
import org.example.clinic_system.dto.responseDTO.RegisterAppointment;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;

import org.example.clinic_system.service.Appointment.AppointmentService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping(value = "appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Operation(summary = "Registrar una nueva cita médica",
            description = "Crea una nueva cita médica asignando un administrador, un doctor y un paciente. Retorna los datos de la cita creada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cita creada correctamente",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "404", description = "Administrador, doctor o paciente no encontrado",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> addAppointment(
            @RequestBody RegisterAppointment registerAppointment) throws NotFoundException {

        Tuple<AppointmentResponseDTO, UUID> response = appointmentService.saveAppointment(
                registerAppointment.getId_admin(),
                registerAppointment.getId_doctor(),
                registerAppointment.getId_patient(),
                registerAppointment.getResponseDTO()
        );

        URI location = UriGeneric.CreateUri("/{id_appointment}", response.getSecond());

        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Se creó la cita correctamente")
                .data(response.getFirst())
                .build();

        return ResponseEntity.created(location).body(successMessage);
    }
}

