package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;

import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.PatientResponseDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.service.Patient.PatientService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/{id_admin}")
    public ResponseEntity<?> createPatient(
            @RequestBody PatientResponseDTO patientResponseDTO,
            @PathVariable UUID id_admin
    ) throws NotFoundException {
        Tuple<PatientResponseDTO, UUID> patient = patientService.savePatient(id_admin, patientResponseDTO);
        URI location = UriGeneric.CreateUri("/{id}",patient.getSecond());
        return ResponseEntity.created(location).body(SuccessMessage.<PatientResponseDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Paciente creado con exito")
                        .data(patient.getFirst())
                        .build());
    }

    @GetMapping("/{id_patient}")
    public ResponseEntity<?> getPatientById(
            @PathVariable UUID id_patient
    ) throws NotFoundException {
        PatientDTO patientDTO = patientService.getPatientDTOById(id_patient);
        return ResponseEntity.ok().body(
                SuccessMessage.<PatientDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Paciente encontrado con exito")
                        .data(patientDTO)
                        .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> getPatientById(@RequestParam String dni) throws NotFoundException {
        PatientDTO patientDTO = patientService.getPatientDTOByDni(dni);
        return ResponseEntity.ok().body(
                SuccessMessage.<PatientDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Paciente encontrado con exito")
                        .data(patientDTO)
                        .build()
        );
    }

    @PatchMapping("/{id_patient}")
    public ResponseEntity<?> updatePatient(
            @RequestBody PatientResponseDTO patientResponseDTO,
            @PathVariable UUID id_patient
    ) throws NotFoundException {
        patientService.updatePatient(id_patient, patientResponseDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id_patient}")
    public ResponseEntity<?> deletePatient(
            @PathVariable UUID id_patient
    ) throws NotFoundException {
        patientService.deletePatient(id_patient);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllPatients(@RequestParam int page) {
        List<PatientDTO>ListPatientDTO = patientService.getAllPatients(page);
        return ResponseEntity.ok().body(
                SuccessMessage.<List<PatientDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de los pacientes de pagina: "+page)
                        .data(ListPatientDTO)
        );
    }

    @GetMapping("/list/")
    public ResponseEntity<?> getAllPatientsByLastName(@RequestParam String lastName,@RequestParam int page) {
        List<PatientDTO>ListPatientDTO = patientService.getAllPatientsByLastName(lastName,page);
        return ResponseEntity.ok().body(
                SuccessMessage.<List<PatientDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de los pacientes de pagina: "+page)
                        .data(ListPatientDTO)
        );
    }

}
