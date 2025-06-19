package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.AppointmentDTO;
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
import java.util.List;
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
                registerAppointment.getDni_patient(),
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

    @PatchMapping("/{id_appointment}")
    public ResponseEntity<?> updateAppointment(@PathVariable UUID id_appointment ,@RequestBody AppointmentResponseDTO appointmentResponseDTO) throws NotFoundException {
        appointmentService.updateAppointment(id_appointment,appointmentResponseDTO);
        SuccessMessage<?>successMessage= SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Se actualizo la cita correctamente")
                .data("Actualiza la tabla")
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @DeleteMapping("/{id_appointment}")
    public ResponseEntity<?> updateAppointment(@PathVariable UUID id_appointment) throws NotFoundException {
        appointmentService.deleteAppointment(id_appointment);
        SuccessMessage<?>successMessage= SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Se elimino la cita correctamente")
                .data("Actualiza la tabla")
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @GetMapping("/{id_appointment}")
    public ResponseEntity<?> getAppointment(@PathVariable UUID id_appointment) throws NotFoundException {
        AppointmentDTO appointmentDTO = appointmentService.getAppointmentDTOById(id_appointment);
        SuccessMessage<?>successMessage= SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Se encontro la cita correctamente")
                .data(appointmentDTO)
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @GetMapping("/patient/{id_patient}")
    public ResponseEntity<?> getAllAppointmentsByIdPatient(@PathVariable UUID id_patient, @RequestParam(defaultValue = "0") int page) throws NotFoundException {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByIdPatient(id_patient, page);
        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Citas del paciente obtenidas correctamente")
                .data(appointments)
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @GetMapping("/doctor/{id_doctor}")
    public ResponseEntity<?> getAllAppointmentsByIdDoctor(@PathVariable UUID id_doctor, @RequestParam(defaultValue = "0") int page) throws NotFoundException {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByIdDoctor(id_doctor, page);
        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Citas del doctor obtenidas correctamente")
                .data(appointments)
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @GetMapping("/doctor/cmp/{cmp}")
    public ResponseEntity<?> getAllAppointmentsByCmpDoctor(@PathVariable String cmp, @RequestParam(defaultValue = "0") int page) throws NotFoundException {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByCmpDoctor(cmp, page);
        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Citas del doctor (por CMP) obtenidas correctamente")
                .data(appointments)
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @GetMapping("/patient/dni/{dni}")
    public ResponseEntity<?> getAllAppointmentsByDniPatient(@PathVariable String dni, @RequestParam(defaultValue = "0") int page) throws NotFoundException {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByDniPatient(dni, page);
        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Citas del paciente (por DNI) obtenidas correctamente")
                .data(appointments)
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAppointments(@RequestParam(defaultValue = "0") int page) {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments(page);
        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Todas las citas obtenidas correctamente")
                .data(appointments)
                .build();
        return ResponseEntity.ok(successMessage);
    }

}
