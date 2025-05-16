package org.example.clinic_system.service.Doctor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseWithIDSpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    Tuple SaveDoctorWithUsername(RegisterDoctorDTO registerDoctorDTO, UUID id_admin, UUID id_specialist) throws NotFoundException;
    Tuple SaveDoctor(RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO,UUID id_admin,UUID id_specialist) throws NotFoundException;
    DoctorDTO getDoctorById(UUID id_doctor) throws NotFoundException;
    List<DoctorDTO> getAllDoctors();
    DoctorResponseWithIDSpecialtyDTO updateDoctor(UUID id_doctor, DoctorResponseWithIDSpecialtyDTO doctorResponseDTO) throws NotFoundException;

    //NEW METHODS
    DoctorDTO getDoctorByCmp(String cmp) throws NotFoundException;
//    DoctorDTO getDoctorByName(String name) throws NotFoundException;
    Doctor getDoctorByIdUser(UUID id_user) throws NotFoundException;
}
