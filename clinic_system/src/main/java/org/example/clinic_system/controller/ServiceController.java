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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("service")
@RequiredArgsConstructor
@Tag(name = "Service", description = "Endpoints para gestión de servicios médicos")
public class ServiceController {

    private final ServiceService serviceService;

    @Operation(summary = "Guardar servicio asignandole una especialidad",
            description = "Crea un nuevo servicio médico asociado a una especialidad específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servicio creado exitosamente",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "404", description = "Especialidad no encontrada",
                    content = @Content)
    })
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

    @Operation(summary = "Obtener servicio por ID",
            description = "Devuelve los detalles de un servicio médico específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio encontrado",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
                    content = @Content)
    })
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

    @Operation(summary = "Actualizar servicio",
            description = "Actualiza los datos de un servicio médico asociado a una especialidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = SuccessMessage.class))),
            @ApiResponse(responseCode = "404", description = "Especialidad o servicio no encontrado",
                    content = @Content)
    })
    @PutMapping("/{specialtyId}")
    public ResponseEntity<?> updateService(
            @RequestBody ServiceDTO serviceDTO,
            @PathVariable("specialtyId") UUID specialtyId) throws NotFoundException {

        ServiceResponseDTO updatedService = serviceService.updateService(serviceDTO, specialtyId);
        SuccessMessage<ServiceResponseDTO> successMessage = SuccessMessage.<ServiceResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Servicio actualizado exitosamente")
                .data(updatedService)
                .build();
        return ResponseEntity.ok(successMessage);
    }

    @Operation(summary = "Eliminar servicio por ID",
            description = "Elimina un servicio médico específico mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servicio eliminado exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Servicio no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") UUID id) throws NotFoundException {
        if (!serviceService.existsServiceById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El servicio con el ID proporcionado no existe");
        }
        serviceService.deleteService(id);
        SuccessMessage<Void> successMessage = SuccessMessage.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("El servicio fue eliminado exitosamente")
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
    }
}
