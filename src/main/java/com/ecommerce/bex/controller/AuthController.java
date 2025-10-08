package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.auth.AuthResponseDTO;
import com.ecommerce.bex.dto.auth.LoginRequestDTO;
import com.ecommerce.bex.dto.auth.RegisterRequestDTO;
import com.ecommerce.bex.service.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SecurityService securityService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO dto){
        return ResponseEntity.ok(securityService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterRequestDTO dto){
        return ResponseEntity.ok(securityService.register(dto));
    }
}
