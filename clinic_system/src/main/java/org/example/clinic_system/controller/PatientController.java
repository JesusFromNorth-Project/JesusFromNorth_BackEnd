package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;

import org.example.clinic_system.service.Patient.PatientService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
/*
    @PostMapping("save")
    public ResponseEntity<SuccessMessage<Patient>> savePatient(@PathVariable UUID userId, @RequestBody PatientDTO patientDTO) {
        Patient patient = patientService.savePatient(patientDTO);
        SuccessMessage<Patient> response = SuccessMessage.<Patient>builder()
                .status(HttpStatus.OK)
                .message("Patient created successfully")
                .data(patient)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findByDni/{dni}")
    public ResponseEntity<SuccessMessage<Patient>> findByDni(@PathVariable UUID userId, @PathVariable String dni) throws NotFoundException {
        Patient patient = patientService.findByDni(dni);
        SuccessMessage<Patient> response = SuccessMessage.<Patient>builder()
                .status(HttpStatus.OK)
                .message("Patient found")
                .data(patient)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<SuccessMessage<Patient>> findById(@PathVariable UUID userId, @PathVariable UUID id) throws NotFoundException {
        Patient patient = patientService.findById(id);
        SuccessMessage<Patient> response = SuccessMessage.<Patient>builder()
                .status(HttpStatus.OK)
                .message("Patient found")
                .data(patient)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteByDni/{dni}")
    public ResponseEntity<SuccessMessage<Void>> deleteByDni(@PathVariable UUID userId, @PathVariable String dni) throws NotFoundException {
        patientService.deleteByDni(dni);
        SuccessMessage<Void> response = SuccessMessage.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .message("Patient deleted by DNI")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("all")
    public ResponseEntity<SuccessMessage<List<Patient>>> findAll(@PathVariable UUID userId) {
        List<Patient> patients = patientService.findAll();
        SuccessMessage<List<Patient>> response = SuccessMessage.<List<Patient>>builder()
                .status(HttpStatus.OK)
                .message("List of patients")
                .data(patients)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SuccessMessage<Patient>> updatePatient(@PathVariable UUID userId, @PathVariable UUID id, @RequestBody PatientDTO patientDTO) throws NotFoundException {
        Patient updatedPatient = patientService.updatePatient(id, patientDTO);
        SuccessMessage<Patient> response = SuccessMessage.<Patient>builder()
                .status(HttpStatus.OK)
                .message("Patient updated successfully")
                .data(updatedPatient)
                .build();
        return ResponseEntity.ok(response);
    }

 */
}
