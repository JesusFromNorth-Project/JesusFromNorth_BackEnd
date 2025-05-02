package org.example.clinic_system.service.Doctor;

import org.example.clinic_system.model.Doctor;
import org.example.clinic_system.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImp(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
       return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findByCmp(String cmp) {
        return doctorRepository.findByCmp(cmp)
                .orElseThrow(() -> new RuntimeException("Doctor con CMP " + cmp + " no encontrado"));
    }

    @Override
    public Doctor findByDni(String dni) {
        return doctorRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Doctor con CMP " + dni + " no encontrado"));
    }

    @Override
    public void deleteByCmp(String cmp) {
        Optional<Doctor> doctor = doctorRepository.findByCmp(cmp);
        doctor.ifPresentOrElse(doctorRepository::delete,
                () -> {
                    throw new RuntimeException("Doctor con CMP " + cmp + " no encontrado, no se puede eliminar");
                });

    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
}
