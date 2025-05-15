package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.SpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyWithServicesDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.ServiceAux.SpecialtyWithService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.Tuple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyWithService specialtyWithService;

    // Endpoint para guardar una especialidad
    @PostMapping
    public ResponseEntity<Tuple> saveSpecialty(@RequestBody SpecialtyResponseDTO specialtyDTO) {
        Tuple result = specialtyService.saveSpecialty(specialtyDTO);
        return ResponseEntity.ok(result);
    }

    // Endpoint para eliminar una especialidad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable("id") UUID id_specialty) {
        try {
            specialtyService.deleteSpecialty(id_specialty);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener todas las especialidades
    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
        List<SpecialtyDTO> specialties = specialtyService.getAllSpecialties();
        return ResponseEntity.ok(specialties);
    }

    // Endpoint para obtener una especialidad con servicios por ID
    @GetMapping("/{id}/services")
    public ResponseEntity<SpecialtyWithServicesDTO> getSpecialtyWithServices(@PathVariable("id") UUID id_specialty) {
        try {
            SpecialtyWithServicesDTO specialtyWithServices = specialtyWithService.getSpecialtyWithServiceDTOById(id_specialty);
            return ResponseEntity.ok(specialtyWithServices);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener una especialidad por su nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<SpecialtyWithServicesDTO> getSpecialtyByName(@PathVariable("name") String nameSpecialty) {
        try {
            SpecialtyWithServicesDTO specialtyWithServices = specialtyWithService.getSpecialtyByNameSpecialty(nameSpecialty);
            return ResponseEntity.ok(specialtyWithServices);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
