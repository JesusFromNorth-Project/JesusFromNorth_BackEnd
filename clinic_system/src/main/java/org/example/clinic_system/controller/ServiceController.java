package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Service.ServiceService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
                .status(HttpStatus.CREATED)
                .message("El servicio fue creado exitosamente")
                .data(response.getFirst())
                .build();

        URI location = UriGeneric.CreateUri("/{serviceId}", response.getSecond());

        return ResponseEntity.created(location).body(successMessage);
    }


    // Endpoint para obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable("id") UUID id) {
        ServiceDTO service = serviceService.getServiceById(id);

        if (service != null) {
            SuccessMessage<ServiceDTO> successMessage = SuccessMessage.<ServiceDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Servicio obtenido correctamente")
                    .data(service)
                    .build();

            return ResponseEntity.ok(successMessage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El servicio con el ID proporcionado no existe");
        }
    }


    // Endpoint para actualizar un servicio de una especialidad
    @PutMapping("/{specialtyId}")
    public ResponseEntity<?> updateService(@RequestBody ServiceDTO serviceDTO, @PathVariable("specialtyId") UUID specialtyId) {
        ServiceResponseDTO updatedService = serviceService.updateService(serviceDTO, specialtyId);

        if (updatedService != null) {
            SuccessMessage<ServiceResponseDTO> successMessage = SuccessMessage.<ServiceResponseDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Servicio actualizado exitosamente")
                    .data(updatedService)
                    .build();

            return ResponseEntity.ok(successMessage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el servicio o la especialidad especificada");
        }
    }

    // Endpoint para eliminar un servicio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") UUID id) {
        if (!serviceService.existsServiceById(id)) { // Verificamos si el ID existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El servicio con el ID proporcionado no existe");
        }
        serviceService.deleteService(id); // Procedemos a eliminar el recurso
        SuccessMessage<Void> successMessage = SuccessMessage.<Void>builder()
                .status(HttpStatus.NO_CONTENT)
                .message("El servicio fue eliminado exitosamente")
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
    }

}