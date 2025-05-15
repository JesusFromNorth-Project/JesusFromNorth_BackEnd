package org.example.clinic_system.service.User;

import org.example.clinic_system.model.User;
import org.example.clinic_system.handler.NotFoundException;

public interface UserService {
    User login(String username, String password) throws NotFoundException;
}
