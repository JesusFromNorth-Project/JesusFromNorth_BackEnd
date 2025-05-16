package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Doctor.DoctorService;

import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Para crear un doctor con usuario y contraseña personalizados
    @PostMapping("/save/assignAdmin/{adminId}/assignSpecialty/{specialistId}")
    public ResponseEntity<?> saveDoctorWithUsername(
            @RequestBody RegisterDoctorDTO registerDoctorDTO,
            @PathVariable("adminId") UUID adminId,
            @PathVariable("specialistId") UUID specialistId) throws NotFoundException {

        Tuple<DoctorResponseDTO, UUID> response = doctorService.SaveDoctorWithUsername(registerDoctorDTO, adminId, specialistId);

        SuccessMessage<DoctorResponseDTO> successMessage  = SuccessMessage.<DoctorResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Se agrego el doctor")
                .data(response.getFirst())
                .build();

        URI location = UriGeneric.CreateUri("/{doctorId}",response.getSecond());

        return ResponseEntity.created(location).body(successMessage);
    }

    // Para crear un doctor con DNI como username y contraseña
    @PostMapping("/{adminId}/{specialistId}")
    public ResponseEntity<?> saveDoctor(
            @RequestBody RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO,
            @PathVariable("adminId") UUID adminId,
            @PathVariable("specialistId") UUID specialistId) throws NotFoundException {
        Tuple<String, String> response = doctorService.SaveDoctor(registerDoctorNoUsernameDTO, adminId, specialistId);
        return ResponseEntity.ok(SuccessMessage.<Tuple<String, String>>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor creado con DNI como nombre de usuario.")
                .data(response)
                .build());
    }


    // Para obtener un doctor por su ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getDoctorById(@PathVariable("doctorId") UUID doctorId) throws NotFoundException {
        DoctorDTO doctor = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor obtenido con éxito.")
                .data(doctor)
                .build());
    }

    // Para obtener todos los doctores
    @GetMapping("list")
    public ResponseEntity<?> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(SuccessMessage.<List<DoctorDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Lista de todos los doctores obtenida con éxito.")
                .data(doctors)
                .build());
    }

    // Para actualizar un doctor por su ID
    @PutMapping("/{doctorId}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable("doctorId") UUID doctorId,
            @RequestBody DoctorResponseDTO doctorResponseDTO) throws NotFoundException {
            DoctorResponseDTO updatedDoctor = doctorService.updateDoctor(doctorId, doctorResponseDTO);
            return ResponseEntity.ok(SuccessMessage.<DoctorResponseDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("Doctor actualizado con éxito.")
                    .data(updatedDoctor)
                    .build());
    }

    // Endpoint para buscar un doctor por CMP
    @GetMapping("/cmp/{cmp}")
    public ResponseEntity<?> getDoctorByCmp(@PathVariable("cmp") String cmp) throws NotFoundException {
            DoctorDTO doctor = doctorService.getDoctorByCmp(cmp);
            return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("Doctor obtenido con éxito por CMP.")
                    .data(doctor)
                    .build());
    }

}
