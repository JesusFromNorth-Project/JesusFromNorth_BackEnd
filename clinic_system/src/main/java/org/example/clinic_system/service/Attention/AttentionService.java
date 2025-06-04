package org.example.clinic_system.service.Attention;

import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.AttentionWithDoctorAndPatientDTO;
import org.example.clinic_system.util.Tuple;

import java.util.List;
import java.util.UUID;

public interface AttentionService {

    //Tarea 1: terminar la logica de atencion
    Tuple<AttentionResponseDTO, UUID> saveAttention(UUID id_appointment,AttentionResponseDTO attentionDTO);
    List<AttentionWithDoctorAndPatientDTO> getListAttention(UUID id_appointment);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdPatient(UUID id_patient);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdDoctor(UUID id_doctor);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByDniPatient(UUID id_patient);
    List<AttentionWithDoctorAndPatientDTO> getListAttentionByCmpDoctor(UUID id_doctor);
    void deleteAttention(UUID id_patient);
}
