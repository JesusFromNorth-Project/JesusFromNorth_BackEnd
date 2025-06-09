package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.*;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.jwt.JwtUtils;
import org.example.clinic_system.service.Doctor.DoctorService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "doctor")
@RequiredArgsConstructor
@Tag(name = "Doctor", description = "Endpoints para gestión de doctores")
public class DoctorController {

    private final DoctorService doctorService;
    private final JwtUtils jwtUtils;

    @Operation(summary = "Crear doctor con usuario y contraseña personalizados",
            description = "Crea un doctor asignando un administrador (extraído del token) y una especialidad, con username y contraseña definidos por el usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor creado exitosamente",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada",
                    content = @Content)
    })
    @PostMapping("/save/assignSpecialty/{specialistId}")
    public ResponseEntity<?> saveDoctorWithUsername(
            @RequestBody RegisterDoctorDTO registerDoctorDTO,
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("specialistId") UUID specialistId) throws NotFoundException {
        
        try {
            // Extraer el token del header
            String token = authorizationHeader.replace("Bearer ", "");
            // Obtener el username del token
            String username = jwtUtils.extractUsername(token);
            
            log.info("Creando doctor con username: {}", username);
            log.info("Datos del doctor: {}", registerDoctorDTO);
            
            // Llamar al servicio con el username del admin
            Tuple<DoctorResponseDTO, UUID> response = doctorService.SaveDoctorWithUsername(
                    registerDoctorDTO, username, specialistId);

            SuccessMessage<DoctorResponseDTO> successMessage = SuccessMessage.<DoctorResponseDTO>builder()
                    .status(HttpStatus.CREATED.value())
                    .message("Se agregó el doctor exitosamente")
                    .data(response.getFirst())
                    .build();

            URI location = UriGeneric.CreateUri("/{doctorId}", response.getSecond());
            return ResponseEntity.created(location).body(successMessage);
            
        } catch (Exception e) {
            log.error("Error al crear doctor", e);
            throw e;
        }
    }

    @Operation(summary = "Crear doctor con DNI como usuario",
            description = "Crea un doctor asignando un administrador (extraído del token) y una especialidad. El DNI será usado como username y password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor creado correctamente",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada",
                    content = @Content)
    })
    @PostMapping("/save/assignSpecialtyNoUsername/{specialistId}")
    public ResponseEntity<?> saveDoctor(
            @RequestBody RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO,
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("specialistId") UUID specialistId) throws NotFoundException {
        
        try {
            // Extraer el token del header
            String token = authorizationHeader.replace("Bearer ", "");
            // Obtener el username del token
            String username = jwtUtils.extractUsername(token);
            
            log.info("Creando doctor con DNI como usuario. Admin: {}", username);
            
            Tuple<DoctorResponseDTO, UUID> response = doctorService.SaveDoctorWithoutUsername(
                    registerDoctorNoUsernameDTO, username, specialistId);
                    
            return ResponseEntity.ok(SuccessMessage.<DoctorResponseDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("Doctor creado con DNI como nombre de usuario.")
                    .data(response.getFirst())
                    .build());
                    
        } catch (Exception e) {
            log.error("Error al crear doctor con DNI como usuario", e);
            throw e;
        }
    }

    @Operation(summary = "Obtener doctor por ID",
            description = "Devuelve los datos de un doctor a partir de su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor encontrado",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "404", description = "Doctor no encontrado",
                    content = @Content)
    })
    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getDoctorById(@PathVariable("doctorId") UUID doctorId) throws NotFoundException {
        DoctorDTO doctor = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(SuccessMessage.<DoctorDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor obtenido con éxito.")
                .data(doctor)
                .build());
    }

    @Operation(summary = "Obtener todos los doctores",
            description = "Devuelve una lista paginada de todos los doctores registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de doctores obtenida",
            content = @Content(schema = @Schema(implementation = SuccessMessage.class)))
    @GetMapping("list")
    public ResponseEntity<?> getAllDoctors(
            @RequestParam(defaultValue = "0") int page) {
        List<DoctorDTO> doctors = doctorService.getAllDoctors(page);
        return ResponseEntity.ok(SuccessMessage.<List<DoctorDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Lista de todos los doctores obtenida con éxito.")
                .data(doctors)
                .build());
    }

    @Operation(summary = "Actualizar doctor",
            description = "Actualiza los datos de un doctor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor actualizado con éxito",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "404", description = "Doctor no encontrado",
                    content = @Content)
    })
    @PutMapping("/{doctorId}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable("doctorId") UUID doctorId,
            @RequestBody DoctorResponseWithIDSpecialtyDTO doctorResponseDTO) throws NotFoundException {

        DoctorResponseWithIDSpecialtyDTO updatedDoctor = doctorService.updateDoctor(doctorId, doctorResponseDTO);
        return ResponseEntity.ok(SuccessMessage.<DoctorResponseWithIDSpecialtyDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor actualizado con éxito.")
                .data(updatedDoctor)
                .build());
    }

    @Operation(summary = "Buscar doctor por CMP",
            description = "Devuelve los datos de un doctor usando su número de colegiatura (CMP).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor encontrado",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "404", description = "Doctor no encontrado por CMP",
                    content = @Content)
    })
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
