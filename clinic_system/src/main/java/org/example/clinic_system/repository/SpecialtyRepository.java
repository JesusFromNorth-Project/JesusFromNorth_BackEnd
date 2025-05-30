package org.example.clinic_system.repository;

import org.example.clinic_system.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, UUID> {
    @Query("SELECT s FROM Specialty s WHERE s.specialty_name = ?1")
    Optional<Specialty> findByName(String name);

    @Query("SELECT s FROM Specialty s WHERE s.is_deleted = false")
    List<Specialty> findAllActive();
}
