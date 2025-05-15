package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Service.ServiceService;
import org.example.clinic_system.util.Tuple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    // Endpoint para guardar un servicio asociado a una especialidad
    @PostMapping("/{specialtyId}")
    public ResponseEntity<Tuple> saveService(@RequestBody ServiceDTO serviceDTO, @PathVariable("specialtyId") UUID specialtyId) {
        try {
            Tuple response = serviceService.saveService(serviceDTO, specialtyId);
            return ResponseEntity.ok(response);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable("id") UUID id) {
        ServiceDTO service = serviceService.getServiceById(id);
        if (service != null) {
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para actualizar un servicio de una especialidad
    @PutMapping("/{specialtyId}")
    public ResponseEntity<ServiceResponseDTO> updateService(@RequestBody ServiceDTO serviceDTO, @PathVariable("specialtyId") UUID specialtyId) {
        ServiceResponseDTO updatedService = serviceService.updateService(serviceDTO, specialtyId);
        if (updatedService != null) {
            return ResponseEntity.ok(updatedService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un servicio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable("id") UUID id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }


}
