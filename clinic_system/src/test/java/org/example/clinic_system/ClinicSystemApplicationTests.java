package org.example.clinic_system;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.RegisterAdminDTO;
import org.example.clinic_system.model.Admin;
import org.example.clinic_system.service.Admin.AdminService;
import org.example.clinic_system.service.Patient.PatientService;
import org.example.clinic_system.util.AdminProcesses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClinicSystemApplicationTests {

    @Autowired
    private AdminService adminService;

    @Test
    void contextLoads() {

    }

    @Test
    void addAdmin(){
        RegisterAdminDTO registerAdminDTO = RegisterAdminDTO.builder()
                .first_name("John")
                .last_name("Smith")
                .dni("123456789")
                .email("Jhon@gmail.com")
                .address("123 Main St")
                .landline_phone("867423")
                .phone("354675453")
                .username("admin23243")
                .password("admin")
                .build();
        adminService.save(registerAdminDTO);
    }

//    @Test
//    void viewPatient(){
//        List<Patient>ListPatient = patientService.findAll();
//        ListPatient.forEach(System.out::println);
//    }

//    @Test
//    void CreatePatient() {
//
//        PatientDTO patient1 = PatientDTO.builder()
//                .first_name("First1")
//                .last_name("Last1")
//                .email("email1@clinic.com")
//                .address("Address 1")
//                .phone("1234567891")
//                .landline_phone("9876543211")
//                .dni("DNI1")
//                .birthdate(LocalDate.of(2000, 1, 1))
//                .gender(Gender.MALE)
//                .age(26)
//                .antecedent("Antecedente 1")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient2 = PatientDTO.builder()
//                .first_name("First2")
//                .last_name("Last2")
//                .email("email2@clinic.com")
//                .address("Address 2")
//                .phone("1234567892")
//                .landline_phone("9876543212")
//                .dni("DNI2")
//                .birthdate(LocalDate.of(2001, 1, 1))
//                .gender(Gender.FEMALE)
//                .age(27)
//                .antecedent("Antecedente 2")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient3 = PatientDTO.builder()
//                .first_name("First3")
//                .last_name("Last3")
//                .email("email3@clinic.com")
//                .address("Address 3")
//                .phone("1234567893")
//                .landline_phone("9876543213")
//                .dni("DNI3")
//                .birthdate(LocalDate.of(2002, 1, 1))
//                .gender(Gender.MALE)
//                .age(28)
//                .antecedent("Antecedente 3")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient4 = PatientDTO.builder()
//                .first_name("First4")
//                .last_name("Last4")
//                .email("email4@clinic.com")
//                .address("Address 4")
//                .phone("1234567894")
//                .landline_phone("9876543214")
//                .dni("DNI4")
//                .birthdate(LocalDate.of(2003, 1, 1))
//                .gender(Gender.FEMALE)
//                .age(29)
//                .antecedent("Antecedente 4")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient5 = PatientDTO.builder()
//                .first_name("First5")
//                .last_name("Last5")
//                .email("email5@clinic.com")
//                .address("Address 5")
//                .phone("1234567895")
//                .landline_phone("9876543215")
//                .dni("DNI5")
//                .birthdate(LocalDate.of(2004, 1, 1))
//                .gender(Gender.MALE)
//                .age(30)
//                .antecedent("Antecedente 5")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient6 = PatientDTO.builder()
//                .first_name("First6")
//                .last_name("Last6")
//                .email("email6@clinic.com")
//                .address("Address 6")
//                .phone("1234567896")
//                .landline_phone("9876543216")
//                .dni("DNI6")
//                .birthdate(LocalDate.of(2005, 1, 1))
//                .gender(Gender.FEMALE)
//                .age(31)
//                .antecedent("Antecedente 6")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient7 = PatientDTO.builder()
//                .first_name("First7")
//                .last_name("Last7")
//                .email("email7@clinic.com")
//                .address("Address 7")
//                .phone("1234567897")
//                .landline_phone("9876543217")
//                .dni("DNI7")
//                .birthdate(LocalDate.of(2006, 1, 1))
//                .gender(Gender.MALE)
//                .age(32)
//                .antecedent("Antecedente 7")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient8 = PatientDTO.builder()
//                .first_name("First8")
//                .last_name("Last8")
//                .email("email8@clinic.com")
//                .address("Address 8")
//                .phone("1234567898")
//                .landline_phone("9876543218")
//                .dni("DNI8")
//                .birthdate(LocalDate.of(2007, 1, 1))
//                .gender(Gender.FEMALE)
//                .age(33)
//                .antecedent("Antecedente 8")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient9 = PatientDTO.builder()
//                .first_name("First9")
//                .last_name("Last9")
//                .email("email9@clinic.com")
//                .address("Address 9")
//                .phone("1234567899")
//                .landline_phone("9876543219")
//                .dni("DNI9")
//                .birthdate(LocalDate.of(2008, 1, 1))
//                .gender(Gender.MALE)
//                .age(34)
//                .antecedent("Antecedente 9")
//                .admin(new Admin())  // Admin placeholder
//                .build();
//
//        PatientDTO patient10 = PatientDTO.builder()
//                .first_name("First10")
//                .last_name("Last10")
//                .email("email10@clinic.com")
//                .address("Address 10")
//                .phone("1234567810")
//                .landline_phone("9876543220")
//                .dni("DNI10")
//                .birthdate(LocalDate.of(2009, 1, 1))
//                .gender(Gender.FEMALE)
//                .age(35)
//                .antecedent("Antecedente 10")
//                .admin(new Admin())
//                .build();
//
//        patientService.save(patient1);
//        patientService.save(patient2);
//        patientService.save(patient3);
//        patientService.save(patient4);
//        patientService.save(patient5);
//        patientService.save(patient6);
//        patientService.save(patient7);
//        patientService.save(patient8);
//        patientService.save(patient9);
//        patientService.save(patient10);
//
//    }
}
