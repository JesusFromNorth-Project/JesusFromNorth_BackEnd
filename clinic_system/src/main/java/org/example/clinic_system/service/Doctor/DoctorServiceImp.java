package org.example.clinic_system.service.Doctor;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseDTO;
import org.example.clinic_system.dto.responseDTO.DoctorResponseWithIDSpecialtyDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorDTO;
import org.example.clinic_system.dto.responseDTO.RegisterDoctorNoUsernameDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.model.Specialty;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;
import org.example.clinic_system.repository.DoctorRepository;
import org.example.clinic_system.repository.UserRepository;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Specialty.SpecialtyService;
import org.example.clinic_system.util.DoctorProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyService specialtyService;
    private final AdminService adminService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Este crear al doctor con su usuario pero necesita que le ingrese el username y el password para crear : retorna (entidad,uuid)
    @Override
    public Tuple<DoctorResponseDTO, UUID> SaveDoctorWithUsername(RegisterDoctorDTO registerDoctorDTO, UUID id_admin, UUID id_specialist) throws NotFoundException {

        Specialty specialty = specialtyService.getSpecialtyById(id_specialist);

        Admin admin = adminService.findById(id_admin);

        User user = User.builder()
                .username(registerDoctorDTO.getUsername())
                .password(passwordEncoder.encode(registerDoctorDTO.getPassword()))
                .role(Rol.DOCTOR)
                .build();

        user = userRepository.save(user);

        Doctor doctorResponseDTO = doctorRepository.save(DoctorProcesses.CreateDoctorWithUsername(registerDoctorDTO,specialty,admin,user));

        return Tuple.
                <DoctorResponseDTO,UUID>builder()
                .first(DoctorProcesses.CreateDoctorEntity(doctorResponseDTO))
                .second(doctorResponseDTO.getId_doctor())
                .build();

    }

    //Este crear al doctor con su usuario pero necesitas solo el password para crear ya que su usename es su dni : retorna (entidad,uuid)
    @Override
    public Tuple<DoctorResponseDTO, UUID> SaveDoctor(RegisterDoctorNoUsernameDTO registerDoctorNoUsernameDTO, UUID id_admin, UUID id_specialist) throws NotFoundException {

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
    public DoctorDTO getDoctorDTOById(UUID id_doctor) throws NotFoundException {
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
    public DoctorResponseWithIDSpecialtyDTO updateDoctor(UUID id_doctor, DoctorResponseWithIDSpecialtyDTO doctorResponseDTO) throws NotFoundException {
        Doctor doc = doctorRepository.findById(id_doctor)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));

        Specialty specialty = specialtyService.getSpecialtyById(doctorResponseDTO.getId_specialty());

        doc = doctorRepository.save(DoctorProcesses.UpdateDoctor(doc,doctorResponseDTO,specialty));

        return DoctorProcesses.CreateDoctorEntityWithIDSpecialty(doc);
    }

    @Override
    public Doctor getDoctorById(UUID id_doctor) throws NotFoundException{
        return doctorRepository.findById(id_doctor)
                .orElseThrow(() -> new NotFoundException("No se encontro al doctor"));
    }

    //para obtener por cmp
    @Override
    public DoctorDTO getDoctorByCmp(String cmp) throws NotFoundException {
        Doctor doctor = doctorRepository.findByCmp(cmp)
                .orElseThrow(() -> new NotFoundException("No se encontró un doctor con el CMP: " + cmp));
        return DoctorProcesses.CreateDoctorDTO(doctor);

    }

    @Override
    public Doctor getDoctorByIdUser(UUID id_user) throws NotFoundException {
        return doctorRepository.findByUser(id_user)
                .orElseThrow( () -> new NotFoundException("No se encontro al doctor"));
    }

    //para obtener por nombres
//    @Override
//    public List<DoctorDTO> getDoctorByName(String name) {
//        List<Doctor> doctors = doctorRepository.findByNameContainingIgnoreCase(name);
//        return DoctorProcesses.CreateDoctorResponseDTO(doctors);
//    }


}
