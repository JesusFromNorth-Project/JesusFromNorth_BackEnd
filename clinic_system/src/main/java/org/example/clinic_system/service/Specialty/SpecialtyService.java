package org.example.clinic_system.service.Specialty;

import org.example.clinic_system.dto.entityDTO.SpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.SpecialtyResponseDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

public interface SpecialtyService {

    Tuple saveSpecialty(SpecialtyResponseDTO specialty);
    void deleteSpecialty(UUID id_specialty) throws NotFoundException;
    List<SpecialtyDTO> getAllSpecialties();

    //Estos metodos se usan en otro servicio
    Specialty getSpecialtyById(UUID id_specialty) throws NotFoundException;
    Specialty getSpecialtyByName(String name) throws NotFoundException;
}
