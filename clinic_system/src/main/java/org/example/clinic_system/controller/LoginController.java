package org.example.clinic_system.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.clinic_system.dto.auth.AuthenticationResponse;
import org.example.clinic_system.dto.entityDTO.LoginDTO;
import org.example.clinic_system.handler.NotFoundException;
import org.example.clinic_system.model.User;
import org.example.clinic_system.repository.UserRepository;
import org.example.clinic_system.service.JwtService;
import org.example.clinic_system.service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            // Authenticate the user credentials using Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );

            // Get the authenticated user from the database
            User user = userRepository.findByUsername(loginDTO.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // Generate JWT tokens
            String accessToken = jwtService.generateToken(
                    (UserDetails) authentication.getPrincipal()
            );
            String refreshToken = jwtService.generateRefreshToken(
                    (UserDetails) authentication.getPrincipal()
            );

            // Create the authentication response with tokens and user info
            AuthenticationResponse response = AuthenticationResponse.fromUserAndTokens(
                    user, accessToken, refreshToken
            );

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during authentication: " + e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token is missing");
        }

        refreshToken = authHeader.substring(7);
        
        try {
            // Extract username from token
            username = jwtService.extractUsername(refreshToken);
            
            if (username != null) {
                // Get user details
                User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                
                UserDetails userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getRole().name())
                        .build();
                
                // Validate refresh token
                if (jwtService.isTokenValid(refreshToken, userDetails)) {
                    // Generate new access token
                    String newAccessToken = jwtService.generateToken(userDetails);
                    
                    // Create response with new access token and existing refresh token
                    AuthenticationResponse response = AuthenticationResponse.fromUserAndTokens(
                            user, newAccessToken, refreshToken
                    );
                    
                    return ResponseEntity.ok(response);
                }
            }
            
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid refresh token");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing refresh token: " + e.getMessage());
        }
    }
    
    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving user information: " + e.getMessage());
        }
    }
}
