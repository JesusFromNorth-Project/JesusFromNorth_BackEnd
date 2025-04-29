package org.example.clinic_system.service.Admin;

import org.example.clinic_system.model.Admin;

public interface AdminService {
    void save(Admin admin);
    Admin findByDni(String dni);
}
