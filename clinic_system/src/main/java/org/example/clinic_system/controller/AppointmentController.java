package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.service.Appointment.AppointmentService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

}
