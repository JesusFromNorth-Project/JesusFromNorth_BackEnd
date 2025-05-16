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

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok().body("permitido");
    }
}
