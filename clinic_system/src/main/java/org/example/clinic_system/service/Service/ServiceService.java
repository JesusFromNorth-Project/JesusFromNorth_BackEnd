package org.example.clinic_system.service.Service;

import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

public interface ServiceService {
    Tuple saveService(ServiceDTO service, UUID id_specialty) throws NotFoundException;
    ServiceDTO getServiceById(UUID id_service);
    ServiceResponseDTO updateService(ServiceDTO service, UUID id_specialty);
    void deleteService(UUID id_service);
    List<ServiceResponseDTO> getAllServicesBySpecialty(UUID id_specialty);
    Boolean existsServiceById(UUID id_service);
}
