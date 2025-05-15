package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Doctor.DoctorService;

import org.example.clinic_system.util.Tuple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Para crear un doctor con usuario y contraseña personalizados
    @PostMapping("/with-username/{adminId}/{specialistId}")
    public ResponseEntity<Tuple> saveDoctorWithUsername(
            @RequestBody RegisterDoctorDTO registerDoctorDTO,
            @PathVariable("adminId") UUID adminId,
            @PathVariable("specialistId") UUID specialistId) {
        try {
            Tuple response = doctorService.SaveDoctorWithUsername(registerDoctorDTO, adminId, specialistId);
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Para crear un doctor con DNI como username y contraseña
    @PostMapping("/{adminId}/{specialistId}")
    public ResponseEntity<Tuple> saveDoctor(
            @RequestBody RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO,
            @PathVariable("adminId") UUID adminId,
            @PathVariable("specialistId") UUID specialistId) {
        try {
            Tuple response = doctorService.SaveDoctor(registerDoctorNoUsernameDTO, adminId, specialistId);
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Para obtener un doctor por su ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("doctorId") UUID doctorId) {
        try {
            DoctorDTO doctor = doctorService.getDoctorById(doctorId);
            return ResponseEntity.ok(doctor);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Para obtener todos los doctores
    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // Para actualizar un doctor por su ID
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(
            @PathVariable("doctorId") UUID doctorId,
            @RequestBody DoctorResponseDTO doctorResponseDTO) {
        try {
            DoctorResponseDTO updatedDoctor = doctorService.updateDoctor(doctorId, doctorResponseDTO);
            return ResponseEntity.ok(updatedDoctor);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para buscar un doctor por CMP
    @GetMapping("/cmp/{cmp}")
    public ResponseEntity<DoctorDTO> getDoctorByCmp(@PathVariable("cmp") String cmp) {
        try {
            DoctorDTO doctor = doctorService.getDoctorByCmp(cmp);
            return ResponseEntity.ok(doctor);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
