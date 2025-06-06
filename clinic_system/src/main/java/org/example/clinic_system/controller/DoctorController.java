package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.dto.responseDTO.*;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Doctor.DoctorService;

import org.example.clinic_system.util.ExcelExporter;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
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
        Tuple<DoctorResponseDTO, UUID> response = doctorService.SaveDoctorWithoutUsername(registerDoctorNoUsernameDTO, adminId, specialistId);
        return ResponseEntity.ok(SuccessMessage.<Tuple<DoctorResponseDTO, UUID>>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor creado con DNI como nombre de usuario.")
                .data(response)
                .build());
    }

    // Para obtener un doctor por su ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getDoctorById(@PathVariable("doctorId") UUID doctorId) throws NotFoundException {
        DoctorDTO doctor = doctorService.getDoctorDTOById(doctorId);
        return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor obtenido con éxito.")
                .data(doctor)
                .build());
    }

    // Para obtener todos los doctores
    @GetMapping("/list")
    public ResponseEntity<?> getAllDoctors(@RequestParam int page) {
        List<DoctorDTO> doctors = doctorService.getAllDoctors(page);
        return ResponseEntity.ok(SuccessMessage.<List<DoctorDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Lista de todos los doctores obtenida con éxito.")
                .data(doctors)
                .build());
    }

    @GetMapping("/list/{specialistId}")
    public ResponseEntity<?> getAllDoctorsBySpecialty(@PathVariable UUID specialistId,@RequestParam int page) {
        List<DoctorDTO> doctors = doctorService.getAllDoctorsBySpecialist(specialistId,page);
        return ResponseEntity.ok(SuccessMessage.<List<DoctorDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Lista de todos los doctores obtenida con éxito.")
                .data(doctors)
                .build());
    }

    // Para actualizar un doctor por su ID
    @PatchMapping("/{doctorId}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable("doctorId") UUID doctorId,
            @RequestBody DoctorResponseWithIDSpecialtyDTO doctorResponseDTO) throws NotFoundException {
        doctorService.updateDoctor(doctorId, doctorResponseDTO);
            return ResponseEntity.ok(SuccessMessage.<String>builder()
                    .status(HttpStatus.OK.value())
                    .message("Doctor actualizado con éxito.")
                    .data("Actualiza la lista")
                    .build());
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable("doctorId") UUID doctorId) throws NotFoundException {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok(SuccessMessage.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor actualizado con éxito.")
                .data("Se borro adecuadamente el doctor")
                .build());
    }

    // Endpoint para buscar un doctor por CMP
    @GetMapping("/getCMP")
    public ResponseEntity<?> getDoctorByCmp(@RequestParam String cmp) throws NotFoundException {
            DoctorDTO doctor = doctorService.getDoctorByCmp(cmp);
            return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("Doctor obtenido con éxito por CMP.")
                    .data(doctor)
                    .build());
    }

    @GetMapping("/getDNI")
    public ResponseEntity<?> getDoctorByDni(@RequestParam String dni) throws NotFoundException {
        DoctorDTO doctor = doctorService.getDoctorByDni(dni);
        return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor obtenido con éxito por CMP.")
                .data(doctor)
                .build());
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportDoctorsToExcel() {
        try {
            List<DoctorDTO> doctors = doctorService.getAllDoctors(0); // Puedes paginar según necesidad
            ByteArrayInputStream stream = ExcelExporter.doctorsToExcel(doctors);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=doctores.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(stream.readAllBytes());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
