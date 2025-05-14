package org.example.clinic_system.util;

import org.example.clinic_system.dto.responseDTO.RegisterAdminDTO;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.User;
import org.example.clinic_system.model.enums.Rol;

public class AdminProcesses {
    public static Admin CreateAdmin(RegisterAdminDTO registerAdminDTO) {
        return Admin.builder()
                .first_name(registerAdminDTO.getFirst_name())
                .last_name(registerAdminDTO.getLast_name())
                .dni(registerAdminDTO.getDni())
                .email(registerAdminDTO.getEmail())
                .address(registerAdminDTO.getAddress())
                .phone(registerAdminDTO.getPhone())
                .landline_phone(registerAdminDTO.getLandline_phone())
                .user(
                        User.builder()
                                .username(registerAdminDTO.getUsername())
                                .password(registerAdminDTO.getPassword())
                                .role(Rol.ADMIN)
                                .build()
                )
                .build();
    }
}
