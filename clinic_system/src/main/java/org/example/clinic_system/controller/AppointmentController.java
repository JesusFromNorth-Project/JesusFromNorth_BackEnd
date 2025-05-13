package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.service.Appointment.AppointmentService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    /*
    @PostMapping("save")
    public ResponseEntity<SuccessMessage<Appointment>> saveAppointment(@PathVariable UUID userId, @RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentService.saveAppointment(appointmentDTO);
        SuccessMessage<Appointment> response = SuccessMessage.<Appointment>builder()
                .status(HttpStatus.OK)
                .message("Appointment created successfully")
                .data(appointment)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<SuccessMessage<Appointment>> findById(@PathVariable UUID userId, @PathVariable UUID id) throws NotFoundException {
        Appointment appointment = appointmentService.findById(id);
        SuccessMessage<Appointment> response = SuccessMessage.<Appointment>builder()
                .status(HttpStatus.OK)
                .message("Appointment found")
                .data(appointment)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findPatientById/{id}")
    public ResponseEntity<SuccessMessage<List<Appointment>>> findPatientById(@PathVariable UUID userId, @PathVariable UUID id) throws NotFoundException {
        List<Appointment> appointments = appointmentService.findPatientById(id);
        SuccessMessage<List<Appointment>> response = SuccessMessage.<List<Appointment>>builder()
                .status(HttpStatus.OK)
                .message("Appointment found")
                .data(appointments)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findAppointmentById/{id}")
    public ResponseEntity<SuccessMessage<List<Appointment>>> findDoctorById(@PathVariable UUID userId, @PathVariable UUID id) throws NotFoundException {
        List<Appointment> appointments = appointmentService.findDoctorById(id);
        SuccessMessage<List<Appointment>> response = SuccessMessage.<List<Appointment>>builder()
                .status(HttpStatus.OK)
                .message("Appointment found")
                .data(appointments)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findAll")
    public ResponseEntity<SuccessMessage<List<Appointment>>> findAll(@PathVariable String dni) {
        List<Appointment> appointments = appointmentService.findAll(dni);
        SuccessMessage<List<Appointment>> response = SuccessMessage.<List<Appointment>>builder()
                .status(HttpStatus.OK)
                .message("List of appointments")
                .data(appointments)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteAppointment/{id}")
    public ResponseEntity<SuccessMessage<Void>> deleteByCmp(@PathVariable UUID userId, @PathVariable UUID id) throws NotFoundException {
        appointmentService.deleteAppointment(id);
        SuccessMessage<Void> response = SuccessMessage.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .message("Appointment deleted successfully")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SuccessMessage<Appointment>> updateAppointment(@PathVariable UUID userId, @PathVariable UUID id, @RequestBody AppointmentDTO appointmentDTO) throws NotFoundException {
        Appointment updatedAppointment = appointmentService.update(id, appointmentDTO);
        SuccessMessage<Appointment> response = SuccessMessage.<Appointment>builder()
                .status(HttpStatus.OK)
                .message("Appointment updated successfully")
                .data(updatedAppointment)
                .build();
        return ResponseEntity.ok(response);
    }
*/
}
