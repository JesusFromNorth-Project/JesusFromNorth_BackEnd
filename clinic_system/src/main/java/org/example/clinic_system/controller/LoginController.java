package org.example.clinic_system.controller;

import org.example.clinic_system.dto.entityDTO.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    /*
    @GetMapping()
    public String LoginController() {
        String username = "admin123";
        String password = "admin123";
        return null;
    }*/


    @PostMapping()
    public ResponseEntity<?> LoginController(@RequestBody LoginDTO loginDTO) {
        if(loginDTO.getUsername().equals("admin123") && loginDTO.getPassword().equals("admin123")) {
            return ResponseEntity.ok().body("Se inicio el usuario");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenciales incorrectas");
    }

}
