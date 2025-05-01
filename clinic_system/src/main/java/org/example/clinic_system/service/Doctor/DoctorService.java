package org.example.clinic_system.service.Doctor;

import org.example.clinic_system.model.Doctor;

public interface DoctorService {
    void save(Doctor doctor);
    Doctor findByCmp(String cmp);
    void delete(Doctor doctor);
    Iterable<Doctor> findAll();

}
