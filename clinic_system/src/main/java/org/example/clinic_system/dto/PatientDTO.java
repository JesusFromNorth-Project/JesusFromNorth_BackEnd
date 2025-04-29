package org.example.clinic_system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Person;
import org.example.clinic_system.model.enums.Gender;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PatientDTO extends Person {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    protected Gender gender;

    protected Integer age;

    protected String antecedent;

    private Admin admin;

}
