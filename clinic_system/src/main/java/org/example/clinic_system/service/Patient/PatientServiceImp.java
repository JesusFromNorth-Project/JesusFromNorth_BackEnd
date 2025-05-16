package org.example.clinic_system.service.Patient;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.PatientDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Patient;
import org.example.clinic_system.repository.PatientRepository;
import org.example.clinic_system.util.PatientProcesses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService{

    private final PatientRepository patientRepository;

//    @Override
//    public Patient savePatient(PatientDTO patientDTO) {
//        Patient patient = PatientProcesses.createPatient(patientDTO);
//        return patientRepository.save(patient);
//    }
//
//    @Override
//    public void deleteByDni(String dni) throws NotFoundException {
//        Optional<Patient> patientOptional = patientRepository.findByDni(dni);
//        if(!patientOptional.isPresent()) {
//            throw new NotFoundException("Patient not found");
//        }
//        Patient Patient = patientOptional.get();
//        Patient.setIs_deleted(true);
//        patientRepository.save(Patient);
//    }
//
//    @Override
//    public Patient findByDni(String dni) throws NotFoundException  {
//        Optional<Patient> patientOptional = patientRepository.findByDni(dni);
//        if(!patientOptional.isPresent() || patientOptional.get().getIs_deleted() ) {
//            throw new NotFoundException("Patient not found or deleted");
//        }
//        return patientOptional.get();
//    }
//
//    @Override
//    public Patient findById(UUID id) throws NotFoundException {
//        Optional<Patient> patientOptional = patientRepository.findById(id);
//        if(!patientOptional.isPresent() || patientOptional.get().getIs_deleted() ) {
//            throw new NotFoundException("Patient not found or deleted");
//        }
//        return patientOptional.get();
//    }
//
//    @Override
//    public List<Patient> findAll() {
//        List<Patient>list = patientRepository.findAll()
//                .stream()
//                .filter(patient -> !patient.getIs_deleted())
//                .toList();
//        return list;
//    }
//
//    @Override
//    public Patient updatePatient(UUID id_patient, PatientDTO patientDTO) throws NotFoundException  {
//        Optional<Patient> patientOptional = patientRepository.findById(id_patient);
//        if(!patientOptional.isPresent() || patientOptional.get().getIs_deleted() ) {
//            throw new NotFoundException("Patient not found");
//        }
//        Patient patient = patientOptional.get();
//        Patient patientUpdated = PatientProcesses.updatePatient(patient, patientDTO);
//        return patientRepository.save(patientUpdated);
//    }

}
