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

    /*
    // Para crear un doctor con usuario y contraseña personalizados
    @PostMapping("/save/assignAdmin/{adminId}/assignSpecialty/{specialistId}")
    public ResponseEntity<?> saveDoctorWithUsername(
            @RequestBody RegisterDoctorDTO registerDoctorDTO,
            @PathVariable("adminId") UUID adminId,
            @PathVariable("specialistId") UUID specialistId) throws NotFoundException {

        Tuple<DoctorResponseDTO, UUID> response = doctorService.SaveDoctorWithUsername(registerDoctorDTO, adminId, specialistId);

        SuccessMessage<DoctorResponseDTO> successMessage  = SuccessMessage.<DoctorResponseDTO>builder()
                .status(HttpStatus.OK)
                .message("Se agrego el doctor")
                .data(response.getFirst())
                .build();

        URI location = UriGeneric.CreateUri("/{doctorId}",response.getSecond());

        return ResponseEntity.created(location).body(successMessage);
    }

    // Para crear un doctor con DNI como username y contraseña
    @PostMapping("/{adminId}/{specialistId}")
    public ResponseEntity<SuccessMessage<Tuple<String, String>>> saveDoctor(
            @RequestBody RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO,
            @PathVariable("adminId") UUID adminId,
            @PathVariable("specialistId") UUID specialistId) {
        try {
            Tuple<String, String> response = doctorService.SaveDoctor(registerDoctorNoUsernameDTO, adminId, specialistId);
            return ResponseEntity.ok(SuccessMessage.<Tuple<String, String>>builder()
                    .status(HttpStatus.OK)
                    .message("Doctor creado con DNI como nombre de usuario.")
                    .data(response)
                    .build());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SuccessMessage.<Tuple<String, String>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Admin o Especialista no encontrado.")
                    .data(null)
                    .build());
        }
    }


    // Para obtener un doctor por su ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<SuccessMessage<DoctorDTO>> getDoctorById(@PathVariable("doctorId") UUID doctorId) {
        try {
            DoctorDTO doctor = doctorService.getDoctorById(doctorId);
            return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Doctor obtenido con éxito.")
                    .data(doctor)
                    .build());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SuccessMessage.<DoctorDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Doctor no encontrado.")
                    .data(null)
                    .build());
        }
    }

    // Para obtener todos los doctores
    @GetMapping
    public ResponseEntity<SuccessMessage<List<DoctorDTO>>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(SuccessMessage.<List<DoctorDTO>>builder()
                .status(HttpStatus.OK)
                .message("Lista de todos los doctores obtenida con éxito.")
                .data(doctors)
                .build());
    }

    // Para actualizar un doctor por su ID
    @PutMapping("/{doctorId}")
    public ResponseEntity<SuccessMessage<DoctorResponseDTO>> updateDoctor(
            @PathVariable("doctorId") UUID doctorId,
            @RequestBody DoctorResponseDTO doctorResponseDTO) {
        try {
            DoctorResponseDTO updatedDoctor = doctorService.updateDoctor(doctorId, doctorResponseDTO);
            return ResponseEntity.ok(SuccessMessage.<DoctorResponseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Doctor actualizado con éxito.")
                    .data(updatedDoctor)
                    .build());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SuccessMessage.<DoctorResponseDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Doctor no encontrado.")
                    .data(null)
                    .build());
        }
    }

    // Endpoint para buscar un doctor por CMP
    @GetMapping("/cmp/{cmp}")
    public ResponseEntity<SuccessMessage<DoctorDTO>> getDoctorByCmp(@PathVariable("cmp") String cmp) {
        try {
            DoctorDTO doctor = doctorService.getDoctorByCmp(cmp);
            return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Doctor obtenido con éxito por CMP.")
                    .data(doctor)
                    .build());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SuccessMessage.<DoctorDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Doctor no encontrado con el CMP proporcionado.")
                    .data(null)
                    .build());
        }
    }


     */

}
