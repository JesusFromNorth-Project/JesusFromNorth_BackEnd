package org.example.clinic_system.dto;

import org.apache.catalina.User;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class DoctorDTO extends UserDTO {
    private Specialty specialty;
    private String cmp;
    protected User user;
    protected Admin admin;

    
}
