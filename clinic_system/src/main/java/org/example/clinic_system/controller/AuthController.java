package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.responseDTO.AuthResponse;
import org.example.clinic_system.dto.responseDTO.LoginDTO;
import org.example.clinic_system.service.User.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //ME DEVUELVE EL TOKEN Y EL USER(ADMIN O DOCTOR)
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginDTO loginDTO) {
        try {
            AuthResponse authResponse = authService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    //RUTA PARA PROBAR EL TOKEN,ESTA PROTEGIDA POR DEFECTO
    //PARA PROBARLA SOLO TIENEN QUE EJECUTAR LA RUTA CON EL TOKEN QUE TE DIO AL INICIAR SESION
    @GetMapping("/check")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok().body("permitido");
    }
}
