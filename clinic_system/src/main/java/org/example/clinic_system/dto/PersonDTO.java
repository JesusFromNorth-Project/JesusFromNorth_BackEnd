package org.example.clinic_system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDTO {

    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String phone;
    private String landline_phone;
    private String dni;

}
