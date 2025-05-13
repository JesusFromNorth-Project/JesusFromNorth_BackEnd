package org.example.clinic_system.dto.entityDTO;

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
public class DoctorDTO extends PersonDTO {
    private Specialty specialty;
    private String cmp;
    private User user;
    private Admin admin;
    private Boolean is_deleted;
}
