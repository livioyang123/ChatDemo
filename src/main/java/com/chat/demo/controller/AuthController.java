package com.chat.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
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
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        List<String> roles = auth.getAuthorities()
                                 .stream()
                                 .map(GrantedAuthority::getAuthority)
                                 .collect(Collectors.toList());

        String jwt = jwtUtil.generateToken(request.getUsername(), roles);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}