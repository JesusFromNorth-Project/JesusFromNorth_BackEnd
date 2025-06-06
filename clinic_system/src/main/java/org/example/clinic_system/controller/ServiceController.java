package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.ServiceSpecialty.ServiceService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    // Endpoint para guardar un servicio asociado a una especialidad
    @PostMapping("/save/assignSpecialty/{specialtyId}")
    public ResponseEntity<?> saveServiceWithDetails(
            @RequestBody ServiceDTO serviceDTO,
            @PathVariable("specialtyId") UUID specialtyId) throws NotFoundException {

        Tuple<ServiceResponseDTO, UUID> response = serviceService.saveService(serviceDTO, specialtyId);
        SuccessMessage<ServiceResponseDTO> successMessage = SuccessMessage.<ServiceResponseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("El servicio fue creado exitosamente")
                .data(response.getFirst())
                .build();

        URI location = UriGeneric.CreateUri("/{serviceId}", response.getSecond());
        return ResponseEntity.created(location).body(successMessage);
    }

    @GetMapping("/list/bySpeciality/{id_specialty}")
    public ResponseEntity<?> getListServices(@PathVariable UUID id_specialty) {
        List<ServiceDTO> list= serviceService.getAllServicesBySpecialty(id_specialty);
        SuccessMessage<?> successMessage = SuccessMessage.builder()
                .status(HttpStatus.OK.value())
                .message("Lista de servicio por la especiliadad")
                .data(list)
                .build();
        return ResponseEntity.ok(successMessage);
    }


    // Endpoint para obtener un servicio por ID (Sin logica)
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable("id") UUID id) throws NotFoundException {
            ServiceDTO service = serviceService.getServiceById(id);
            SuccessMessage<ServiceDTO> successMessage = SuccessMessage.<ServiceDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("Servicio obtenido correctamente")
                    .data(service)
                    .build();

            return ResponseEntity.ok(successMessage);
    }


    // Endpoint para actualizar un servicio de una especialidad (Sin logica)
    @PutMapping("/{specialtyId}")
    public ResponseEntity<?> updateService(@RequestBody ServiceDTO serviceDTO, @PathVariable("specialtyId") UUID specialtyId) throws NotFoundException {
        return null;
    }

    // Endpoint para eliminar un servicio por ID(Sin logica)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") UUID id) throws NotFoundException {
        return null;
    }



}