package org.example.clinic_system.service.Doctor;

import org.example.clinic_system.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor save(Doctor doctor);
    Doctor findByCmp(String cmp);
    Doctor findByDni(String dni);
    void deleteByCmp(String cmp);
    List<Doctor> findAll();
}
