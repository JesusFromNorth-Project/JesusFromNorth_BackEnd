package org.example.clinic_system.service.Doctor;

import org.example.clinic_system.dto.entityDTO.DoctorDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.repository.DoctorRepository;
import org.example.clinic_system.util.DoctorProcesses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImp(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(DoctorDTO doctorDTO) {
       Doctor doctor = DoctorProcesses.CreateDoctor(doctorDTO);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findByCmp(String cmp) throws NotFoundException {
        Optional<Doctor>doctorOptional = doctorRepository.findByCmp(cmp);
        if (!doctorOptional.isPresent() || doctorOptional.get().getIs_deleted()) {
            throw new NotFoundException("Doctor not found or delete");
        }
        return doctorOptional.get();
    }

    @Override
    public Doctor findByDni(String dni) throws NotFoundException {
        Optional<Doctor>doctorOptional = doctorRepository.findByDni(dni);
        if (!doctorOptional.isPresent() || doctorOptional.get().getIs_deleted()) {
            throw new NotFoundException("Doctor not found or delete");
        }
        return doctorOptional.get();
    }

    @Override
    public Doctor findByidDoctor(UUID id) throws NotFoundException {
        Optional<Doctor>doctorOptional = doctorRepository.findById(id);
        if (!doctorOptional.isPresent() || doctorOptional.get().getIs_deleted()) {
            throw new NotFoundException("Doctor not found or delete");
        }
        return doctorOptional.get();
    }

    @Override
    public void deleteByCmp(String cmp) throws NotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findByCmp(cmp);
        if(!doctorOptional.isPresent() || doctorOptional.get().getIs_deleted()) {
            throw new NotFoundException("Doctor not found or delete");
        }
        Doctor doctor = doctorOptional.get();
        doctor.setIs_deleted(true);
        doctorRepository.save(doctor);
    }

    @Override
    public void deleteByDni(String dni) throws NotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findByDni(dni);
        if(!doctorOptional.isPresent() || doctorOptional.get().getIs_deleted()) {
            throw new NotFoundException("Doctor not found or delete");
        }
        Doctor doctor = doctorOptional.get();
        doctor.setIs_deleted(true);
        doctorRepository.save(doctor);
    }

    @Override
    public void deleteByidDoctor(UUID id) throws NotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(!doctorOptional.isPresent() || doctorOptional.get().getIs_deleted()) {
            throw new NotFoundException("Doctor not found or delete");
        }
        Doctor doctor = doctorOptional.get();
        doctor.setIs_deleted(true);
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll()
                .stream()
                .filter(doctor -> !doctor.getIs_deleted())
                .toList();
    }

    @Override
    public Doctor UpdateDoctor(UUID id, DoctorDTO doctorDTO) throws NotFoundException {
        Optional<Doctor>DoctorOptional = doctorRepository.findById(id);
        if(!DoctorOptional.isPresent() || DoctorOptional.get().getIs_deleted()) {
            throw new NotFoundException("Doctor not found or delete");
        }
        Doctor doctorUpdate = DoctorProcesses.UpdateDoctor(DoctorOptional.get(), doctorDTO);
        return doctorRepository.save(doctorUpdate);
    }
}
