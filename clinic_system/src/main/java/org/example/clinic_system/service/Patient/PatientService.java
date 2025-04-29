package org.example.clinic_system.service.Patient;

import org.example.clinic_system.dto.PatientDTO;
import org.example.clinic_system.model.Patient;

import java.util.List;


public interface PatientService {

    void save(PatientDTO patientDTO);

    void deleteByDni(String dni);

    Patient findByDni(String dni);

    List<Patient> findAll();

}
