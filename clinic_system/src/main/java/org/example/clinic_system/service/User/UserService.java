package org.example.clinic_system.service.User;

import org.example.clinic_system.dto.entityDTO.UserDTO;
import org.example.clinic_system.dto.responseDTO.LoginDTO;
import org.example.clinic_system.handler.NotFoundException;

public interface UserService {
    UserDTO findUser(LoginDTO loginDTO) throws NotFoundException;
}
