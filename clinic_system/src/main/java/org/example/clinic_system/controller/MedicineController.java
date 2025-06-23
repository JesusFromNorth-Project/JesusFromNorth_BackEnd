package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.MedicineDTO;
import org.example.clinic_system.dto.responseDTO.MedicineResponseDTO;
import org.example.clinic_system.dto.responseDTO.SuccessMessage;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.service.Medicine.MedicineService;
import org.example.clinic_system.util.Tuple;
import org.example.clinic_system.util.UriGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("medicine")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/{id_admin}")
    public ResponseEntity<?> createMedicine(
            @RequestBody MedicineResponseDTO medicineResponseDTO,
            @PathVariable UUID id_admin
    ) throws NotFoundException {
        Tuple<MedicineResponseDTO, UUID> medicine = medicineService.SaveMedicine(medicineResponseDTO, id_admin);
        URI location = UriGeneric.CreateUri("/{id_medicine}", medicine.getSecond());
        return ResponseEntity.created(location).body(SuccessMessage.<MedicineResponseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Medicamento creado con exito")
                .data(medicine.getFirst())
                .build());
    }

    @GetMapping("/id_medicine")
    public ResponseEntity<?> getMedicineById(
            @PathVariable UUID id_medicine
    ) throws NotFoundException {
        MedicineDTO medicineDTO = medicineService.getMedicineDTOById(id_medicine);
        return ResponseEntity.ok().body(
                SuccessMessage.<MedicineDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Medicamento encontrado con exito")
                        .data(medicineDTO)
                        .build());
    }

    @GetMapping("/")
    public ResponseEntity<?> getMedicineByName(@RequestParam String name_medicine) throws NotFoundException {
        MedicineDTO medicineDTO = medicineService.getMedicineDTOByName(name_medicine);
        return ResponseEntity.ok().body(
                SuccessMessage.<MedicineDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Medicamento encontrado con exito")
                        .data(medicineDTO)
                        .build()
        );
    }

    @PatchMapping("/{id_medicine}")
    public ResponseEntity<?> updateMedicine(
            @RequestBody MedicineResponseDTO medicineResponseDTO,
            @PathVariable UUID id_medicine
    ) throws NotFoundException {
        medicineService.updateMedicine(id_medicine, medicineResponseDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id_medicine}")
    public ResponseEntity<?> deleteMedicine(
            @PathVariable UUID id_medicine
    ) throws NotFoundException {
        medicineService.deleteMedicine(id_medicine);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllMedicines(@RequestParam int page) {
        List<MedicineDTO> listMedicineDTO = medicineService.getAllMedicines(page);
        return ResponseEntity.ok().body(
                SuccessMessage.<List<MedicineDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de las medicinas de pagina: " + page)
                        .data(listMedicineDTO)
                        .build()
        );
    }

    @GetMapping("/list/by-type")
    public ResponseEntity<?> getAllMedicinesByType(
            @RequestParam String type_medicine,
            @RequestParam int page
    ) {
        List<MedicineDTO> listMedicineDTO = medicineService.getAllMedicinesByType(type_medicine, page);
        return ResponseEntity.ok().body(
                SuccessMessage.<List<MedicineDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de las medicinas de pagina: " + page)
                        .data(listMedicineDTO)
                        .build()
        );
    }

    @GetMapping("/list/by-date")
    public ResponseEntity<?> getAllMedicinesByDate(
            @RequestParam LocalDate date,
            @RequestParam int page
    ) {
        List<MedicineDTO> listMedicineDTO = medicineService.getAllMedicinesByDate(date, page);
        return ResponseEntity.ok().body(
                SuccessMessage.<List<MedicineDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lista de las medicinas de pagina: " + page)
                        .data(listMedicineDTO)
                        .build()
        );
    }

}
