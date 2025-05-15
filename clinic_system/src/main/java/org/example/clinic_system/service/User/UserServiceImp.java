package org.example.clinic_system.service.User;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.User;
import org.example.clinic_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String username, String password) throws NotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (user.getIs_deleted()) {
            throw new NotFoundException("Usuario eliminado");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new NotFoundException("Contraseña incorrecta");
        }

        return user;
    }
}
