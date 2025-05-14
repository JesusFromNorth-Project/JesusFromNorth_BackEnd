package org.example.clinic_system.service.Doctor;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.repository.DoctorRepository;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.DoctorProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyService specialtyService;
    private final AdminService adminService;

    //Este crear al doctor con su usuario pero necesita que le ingrese el username y el password para crear : retorna (entidad,uuid)
    @Override
    public Tuple SaveDoctorWithUsername(RegisterDoctorDTO registerDoctorDTO, UUID id_admin, UUID id_specialist) throws NotFoundException {
        Specialty specialty = specialtyService.getSpecialtyById(id_specialist);
        Admin admin = adminService.findById(id_admin);
        Doctor doctorResponseDTO = doctorRepository.save(DoctorProcesses.CreateDoctorWithUsername(registerDoctorDTO,specialty,admin));

        return Tuple.
                <DoctorResponseDTO,UUID>builder()
                .first(DoctorProcesses.CreateDoctorEntity(doctorResponseDTO))
                .second(doctorResponseDTO.getId_doctor())
                .build();

    }

    //Este crear al doctor con su usuario pero necesitas solo el password para crear ya que su usename es su dni : retorna (entidad,uuid)
    @Override
    public Tuple SaveDoctor(RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO, UUID id_admin, UUID id_specialist) throws NotFoundException {

        Specialty specialty = specialtyService.getSpecialtyById(id_specialist);
        Admin admin = adminService.findById(id_admin);
        Doctor doctorResponseDTO = doctorRepository.save(DoctorProcesses.CreateDoctorNoUsername(registerDoctorNoUsernameDTO,specialty,admin));

        return Tuple.
                <DoctorResponseDTO,UUID>builder()
                .first(DoctorProcesses.CreateDoctorEntity(doctorResponseDTO))
                .second(doctorResponseDTO.getId_doctor())
                .build();
    }

    @Override
    public DoctorDTO getDoctorById(UUID id_doctor) throws NotFoundException {
        Doctor doc = doctorRepository.findById(id_doctor)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
        return DoctorProcesses.CreateDoctorDTO(doc);
    }

    //Me trae todos los doctores
    @Override
    public List<DoctorDTO> getAllDoctors() {
        return DoctorProcesses
                .CreateDoctorResponseDTO(doctorRepository.findAll());
    }

    //Este es para actualizar el doctor incluso su tipo de especialidad
    @Override
    public DoctorResponseDTO updateDoctor(UUID id_doctor, DoctorResponseDTO doctorResponseDTO) throws NotFoundException {
        Doctor doc = doctorRepository.findById(id_doctor)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
        doc = doctorRepository.save(DoctorProcesses.UpdateDoctor(doc,doctorResponseDTO));
        return DoctorProcesses.CreateDoctorEntity(doc);
    }

}
