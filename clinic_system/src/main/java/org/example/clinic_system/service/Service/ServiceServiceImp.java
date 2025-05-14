package org.example.clinic_system.service.Service;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.ServiceDTO;
import org.example.clinic_system.dto.responseDTO.ServiceResponseDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.ServiceSpecialty;
import org.example.clinic_system.util.SpecialtyProcesses;
import org.springframework.stereotype.Service;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.repository.ServiceRepository;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.ServiceProcesses;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceServiceImp implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final SpecialtyService specialtyService;

    //ME GUARDA UN SERVICIO
    @Override
    public Tuple saveService(ServiceDTO serviceDTO, UUID id_specialty) throws NotFoundException {
        Specialty specialty = specialtyService.getSpecialtyById(id_specialty);
        ServiceSpecialty serviceSpecialty = serviceRepository.save(ServiceProcesses.getService(serviceDTO,specialty));
        return Tuple.
                <ServiceResponseDTO,UUID> builder()
                .first(ServiceProcesses.getSpecialty(serviceSpecialty))
                .second(specialty.getId_specialty())
                .build();
    }

    @Override
    public ServiceDTO getServiceById(UUID id_service) {
        return null;
    }

    @Override
    public ServiceResponseDTO updateService(ServiceDTO service, UUID id_specialty) {
        return null;
    }

    @Override
    public void deleteService(UUID id_service) {

    }

    //Me devuelve todos los servicios de una especialidad,LO UTILIZA OTROSERVICIO,NO SE VA USAR EN EL CONTROLADOR
    @Override
    public List<ServiceResponseDTO> getAllServicesBySpecialty(UUID id_specialty) {
        return ServiceProcesses
                .TransformListServiceResponseDTO(serviceRepository.findBySpecialtyId(id_specialty));
    }


}
