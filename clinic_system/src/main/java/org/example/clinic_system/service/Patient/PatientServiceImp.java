package org.example.clinic_system.service.Patient;

import org.example.clinic_system.dto.PatientDTO;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.repository.PatientRepository;
import org.example.clinic_system.util.PatientProcesses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService{

    private final PatientRepository patientRepository;

    public PatientServiceImp(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void save(PatientDTO patientDTO) {
        Patient patient = PatientProcesses.createPatient(patientDTO);
        patientRepository.save(patient);
    }

    @Override
    public void deleteByDni(String dni) {
        patientRepository.deleteByDni(dni);
    }

    @Override
    public Patient findByDni(String dni) {
        return patientRepository.findByDni(dni).orElse(null);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }


}
