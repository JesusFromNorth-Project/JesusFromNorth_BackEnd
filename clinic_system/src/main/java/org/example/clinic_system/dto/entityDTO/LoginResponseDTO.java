package org.example.clinic_system.dto.entityDTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginResponseDTO {
    private UUID id;
    private String username;
    private String role;

    public static LoginResponseDTO fromUser(org.example.clinic_system.model.User user) {
        return LoginResponseDTO.builder()
                .id(user.getId_user())
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
