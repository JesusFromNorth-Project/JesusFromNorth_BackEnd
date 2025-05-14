package org.example.clinic_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.entityDTO.UserDTO;
import org.example.clinic_system.dto.responseDTO.LoginDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.User;
import org.example.clinic_system.service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/sesion")
    public UserDTO GetUser(@RequestBody LoginDTO loginDTO) throws NotFoundException {
        return userService.findUser(loginDTO);
    }

    @GetMapping("/list")
    public List<User> GetUser() throws NotFoundException {
        return userService.findAllUsers();
    }


}
