package com.chat.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chat.demo.Util.JwtUtil;
import com.chat.demo.data.dto.AuthResponse;
import com.chat.demo.data.dto.LoginMessage;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginMessage request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            List<String> roles = auth.getAuthorities()
                                     .stream()
                                     .map(GrantedAuthority::getAuthority)
                                     .collect(Collectors.toList());

            String jwt = jwtUtil.generateToken(request.getUsername(), roles);
            return ResponseEntity.ok(new AuthResponse(jwt));
            
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenziali non valide: " + e.getMessage());
        }
    }

    // Fix: aggiunta la "/" mancante
    @PostMapping("/login/test")
    public ResponseEntity<String> loginTest(@RequestBody LoginMessage request) {
        return ResponseEntity.ok("User " + request.getUsername() + 
                " test login successful with password: " + request.getPassword());
    }
}