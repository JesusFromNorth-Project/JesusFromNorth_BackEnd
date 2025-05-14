package org.example.clinic_system.service.Admin;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.RegisterAdminDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.repository.AdminRepository;
import org.example.clinic_system.util.AdminProcesses;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public void save(RegisterAdminDTO registerAdminDTO) {
        Admin newAdmin = AdminProcesses.CreateAdmin(registerAdminDTO);
        adminRepository.save(newAdmin);
    }

    @Override
    public Admin findByDni(String dni) throws NotFoundException {
        return adminRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("No se encontro al admin"));
    }

    @Override
    public Admin findById(UUID id_admin) throws NotFoundException {
        return adminRepository.findById(id_admin)
                .orElseThrow(() -> new NotFoundException("No se encontro al admin"));
    }

    @Override
    public Admin findByUser(UUID id_user) throws NotFoundException {
        return adminRepository.findByUser(id_user)
                .orElseThrow(() -> new NotFoundException("No se encontro al admin"));
    }

}
