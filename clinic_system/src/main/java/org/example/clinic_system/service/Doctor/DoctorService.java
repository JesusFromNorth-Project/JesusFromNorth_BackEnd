package org.example.clinic_system.service.Doctor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    Doctor save(DoctorDTO doctorDTO);
    Doctor findByCmp(String cmp) throws NotFoundException;
    Doctor findByDni(String dni) throws NotFoundException;
    Doctor findByidDoctor(UUID id) throws NotFoundException;
    void deleteByCmp(String cmp) throws NotFoundException;
    void deleteByDni(String dni) throws NotFoundException;
    void deleteByidDoctor(UUID id) throws NotFoundException;
    List<Doctor> findAll();
    Doctor UpdateDoctor(UUID id, DoctorDTO doctorDTO) throws NotFoundException;
}
