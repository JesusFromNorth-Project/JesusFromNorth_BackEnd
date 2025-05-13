package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;

import org.example.clinic_system.service.Doctor.DoctorService;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
/*
    @PostMapping("save")
    public ResponseEntity<SuccessMessage<Doctor>> saveDoctor(@PathVariable UUID userId, @RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorService.save(doctorDTO);
        SuccessMessage<Doctor> response = SuccessMessage.<Doctor>builder()
                .status(HttpStatus.OK)
                .message("Doctor created successfully")
                .data(doctor)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findByCmp/{cmp}")
    public ResponseEntity<SuccessMessage<Doctor>> findByCmp(@PathVariable UUID userId, @PathVariable String cmp) throws NotFoundException {
        Doctor doctor = doctorService.findByCmp(cmp);
        SuccessMessage<Doctor> response = SuccessMessage.<Doctor>builder()
                .status(HttpStatus.OK)
                .message("Doctor found")
                .data(doctor)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findByDni/{dni}")
    public ResponseEntity<SuccessMessage<Doctor>> findByDni(@PathVariable UUID userId, @PathVariable String dni) throws NotFoundException {
        Doctor doctor = doctorService.findByDni(dni);
        SuccessMessage<Doctor> response = SuccessMessage.<Doctor>builder()
                .status(HttpStatus.OK)
                .message("Doctor found")
                .data(doctor)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<SuccessMessage<Doctor>> findById(@PathVariable UUID userId, @PathVariable UUID id) throws NotFoundException {
        Doctor doctor = doctorService.findByidDoctor(id);
        SuccessMessage<Doctor> response = SuccessMessage.<Doctor>builder()
                .status(HttpStatus.OK)
                .message("Doctor found")
                .data(doctor)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteByCmp/{cmp}")
    public ResponseEntity<SuccessMessage<Void>> deleteByCmp(@PathVariable UUID userId, @PathVariable String cmp) throws NotFoundException {
        doctorService.deleteByCmp(cmp);
        SuccessMessage<Void> response = SuccessMessage.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .message("Doctor deleted by CMP")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteByDni/{dni}")
    public ResponseEntity<SuccessMessage<Void>> deleteByDni(@PathVariable UUID userId, @PathVariable String dni) throws NotFoundException {
        doctorService.deleteByDni(dni);
        SuccessMessage<Void> response = SuccessMessage.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .message("Doctor deleted by DNI")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<SuccessMessage<Void>> deleteById(@PathVariable UUID userId, @PathVariable UUID id) throws NotFoundException {
        doctorService.deleteByidDoctor(id);
        SuccessMessage<Void> response = SuccessMessage.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .message("Doctor deleted by ID")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("all")
    public ResponseEntity<SuccessMessage<List<Doctor>>> findAll(@PathVariable UUID userId) {
        List<Doctor> doctors = doctorService.findAll();
        SuccessMessage<List<Doctor>> response = SuccessMessage.<List<Doctor>>builder()
                .status(HttpStatus.OK)
                .message("List of doctors")
                .data(doctors)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SuccessMessage<Doctor>> updateDoctor(@PathVariable UUID userId, @PathVariable UUID id, @RequestBody DoctorDTO doctorDTO) throws NotFoundException {
        Doctor updatedDoctor = doctorService.UpdateDoctor(id, doctorDTO);
        SuccessMessage<Doctor> response = SuccessMessage.<Doctor>builder()
                .status(HttpStatus.OK)
                .message("Doctor updated successfully")
                .data(updatedDoctor)
                .build();
        return ResponseEntity.ok(response);
    }

 */
}
