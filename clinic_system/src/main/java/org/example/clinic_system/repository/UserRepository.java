package org.example.clinic_system.repository;

import org.example.clinic_system.dto.entityDTO.UserDTO;
import org.example.clinic_system.dto.responseDTO.LoginDTO;
import org.example.clinic_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);
}
