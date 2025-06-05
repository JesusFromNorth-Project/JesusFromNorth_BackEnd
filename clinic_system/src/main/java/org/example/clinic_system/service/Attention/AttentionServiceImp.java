package org.example.clinic_system.service.Attention;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.AttentionWithDoctorAndPatientDTO;
import org.example.clinic_system.repository.AttentionRepository;
import org.example.clinic_system.service.Appointment.AppointmentService;
import org.example.clinic_system.util.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttentionServiceImp implements AttentionService{

    private final AttentionRepository attentionRepository;
    private final AppointmentService appointmentService;

    @Override
    public Tuple<AttentionResponseDTO, UUID> saveAttention(UUID id_appointment, AttentionResponseDTO attentionDTO) {
        return null;
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttention(UUID id_appointment) {
        return List.of();
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdPatient(UUID id_patient) {
        return List.of();
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByIdDoctor(UUID id_doctor) {
        return List.of();
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByDniPatient(UUID id_patient) {
        return List.of();
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttentionByCmpDoctor(UUID id_doctor) {
        return List.of();
    }

    @Override
    public void deleteAttention(UUID id_patient) {

    }

}
