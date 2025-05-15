package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.SpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtySavedResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.ServiceAux.SpecialtyWithService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyWithService specialtyWithService;

    // Endpoint para guardar una especialidad
    @PostMapping("/")
    public ResponseEntity<SuccessMessage<SpecialtyResponseDTO>> saveSpecialty(@RequestBody SpecialtyResponseDTO specialtyDTO) {
        Tuple<SpecialtyResponseDTO, UUID> result = specialtyService.saveSpecialty(specialtyDTO);
        SuccessMessage<SpecialtyResponseDTO> successMessage = SuccessMessage.<SpecialtyResponseDTO>builder()
                .status(HttpStatus.CREATED.value())  // Usa el código numérico
                .message("La especialidad fue guardada exitosamente")
                .data(result.getFirst())
                .build();
        URI location = UriGeneric.CreateUri("/{id", result.getSecond());
        return ResponseEntity.created(location).body(successMessage);
    }



    // Endpoint para eliminar una especialidad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecialty(@PathVariable("id") UUID id_specialty) throws NotFoundException {
            specialtyService.deleteSpecialty(id_specialty);
            SuccessMessage<Void> successMessage = SuccessMessage.<Void>builder()
                    .status(HttpStatus.NO_CONTENT.value())
                    .message("La especialidad fue eliminada exitosamente")
                    .build();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
    }


    // Endpoint para obtener todas las especialidades
    @GetMapping("/list")
    public ResponseEntity<?> getAllSpecialties() {
        List<SpecialtyDTO> specialties = specialtyService.getAllSpecialties();
        SuccessMessage<List<SpecialtyDTO>> successMessage = SuccessMessage.<List<SpecialtyDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Lista de especialidades obtenida correctamente")
                .data(specialties)
                .build();

        return ResponseEntity.ok(successMessage);
    }


    // Endpoint para obtener una especialidad con servicios por ID
    @GetMapping("/{id}/services")
    public ResponseEntity<?> getSpecialtyWithServices(@PathVariable("id") UUID id_specialty) throws NotFoundException {
            SpecialtyWithServicesDTO specialtyWithServices = specialtyWithService.getSpecialtyWithServiceDTOById(id_specialty);
            SuccessMessage<SpecialtyWithServicesDTO> successMessage = SuccessMessage.<SpecialtyWithServicesDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("La especialidad con los servicios fue obtenida correctamente")
                    .data(specialtyWithServices)
                    .build();
            return ResponseEntity.ok(successMessage);
    }


    // Endpoint para obtener una especialidad por su nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getSpecialtyByName(@PathVariable("name") String nameSpecialty) throws NotFoundException {
            SpecialtyWithServicesDTO specialtyWithServices = specialtyWithService.getSpecialtyByNameSpecialty(nameSpecialty);
            SuccessMessage<SpecialtyWithServicesDTO> successMessage = SuccessMessage.<SpecialtyWithServicesDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("La especialidad con servicios fue obtenida exitosamente por nombre")
                    .data(specialtyWithServices)
                    .build();
            return ResponseEntity.ok(successMessage);
    }

}
