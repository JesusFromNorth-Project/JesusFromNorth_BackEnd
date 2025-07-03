package org.example.clinic_system.service.Attention;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.AttentionWithDoctorAndPatientDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.model.Attention;
import org.example.clinic_system.repository.AttentionRepository;
import org.example.clinic_system.service.Appointment.AppointmentService;
import org.example.clinic_system.service.ServiceAux.ServiceAuxPrescription.PrescriptionAuxService;
import org.example.clinic_system.util.AttentionProcesses;
import org.example.clinic_system.util.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttentionServiceImp implements AttentionService{

    private final AttentionRepository attentionRepository;
    private final AppointmentService appointmentService;
    private final PrescriptionAuxService prescriptionAuxService;

    @Override
    public Tuple<AttentionResponseDTO, UUID> saveAttention(UUID id_appointment, AttentionResponseDTO attentionDTO) throws NotFoundException {
        Appointment appointment = appointmentService.getAppointmentById(id_appointment);
        Attention attention = attentionRepository.save(AttentionProcesses.ToAttention(appointment, attentionDTO));
        prescriptionAuxService.savePrescription(attention,attentionDTO.getPrescriptions());
        return new Tuple<AttentionResponseDTO,UUID>(
                AttentionProcesses.ToAttentionDTO(attention,attentionDTO.getPrescriptions()),
                attention.getId_attention()
        );
    }

    @Override
    public List<AttentionWithDoctorAndPatientDTO> getListAttention() {
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
    public Attention getAttentionById(UUID id) throws NotFoundException {
        return attentionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Atencion no encontrada")
        );
    }

    @Override
    public void deleteAttention(UUID id_patient) {

    }

}
