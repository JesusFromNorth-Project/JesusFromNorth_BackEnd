package org.example.clinic_system.util;

import org.example.clinic_system.dto.responseDTO.AttentionResponseDTO;
import org.example.clinic_system.dto.responseDTO.PrescriptionItemResponseDTO;
import org.example.clinic_system.model.Appointment;
import org.example.clinic_system.model.Attention;

import java.util.List;


public class AttentionProcesses {
    public static Attention ToAttention(Appointment appointment, AttentionResponseDTO attentionResponseDTO) {
        return Attention.builder()
                .attentionType(attentionResponseDTO.getAttentionType())
                .appointment(appointment)
                .diagnosis(attentionResponseDTO.getDiagnosis())
                .treatment(attentionResponseDTO.getTreatment())
                .build();
    }

    public static AttentionResponseDTO ToAttentionDTO(Attention attentionResponseDTO,List<PrescriptionItemResponseDTO> prescriptions) {
        return AttentionResponseDTO.builder()
                .attentionType(attentionResponseDTO.getAttentionType())
                .prescriptions(prescriptions) // Puede aver errores ya estoy usando las que paso el usuario,no las que estan en la bd
                .diagnosis(attentionResponseDTO.getDiagnosis())
                .treatment(attentionResponseDTO.getTreatment())
                .build();
    }

}
