package org.example.clinic_system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.enums.Gender;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO extends PersonDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private Gender gender;
    private Integer age;
    private String antecedent;
    private Admin admin;
    private Boolean is_deleted;
}
