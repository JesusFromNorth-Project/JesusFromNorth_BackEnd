package org.example.clinic_system.service.User;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.UserDTO;
import org.example.clinic_system.dto.responseDTO.LoginDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.User;
import org.example.clinic_system.repository.UserRepository;
import org.example.clinic_system.util.UserProcesses;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO findUser(LoginDTO loginDTO) throws NotFoundException {
        User Finduser = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow( () -> new NotFoundException("No se encontro el user"));
        return UserProcesses.CreateuserDTO(Finduser);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
