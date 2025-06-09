package org.example.clinic_system.repository;

import org.example.clinic_system.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
//    @Query(Selec)
//    List<Medicine> findMedicineByMedicineId(UUID medicineId);

}
