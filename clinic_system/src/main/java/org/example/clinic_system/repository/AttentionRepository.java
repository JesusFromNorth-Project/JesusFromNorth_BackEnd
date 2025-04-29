package org.example.clinic_system.repository;

import org.example.clinic_system.model.Attention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttentionRepository extends JpaRepository<Attention, UUID> {
}
