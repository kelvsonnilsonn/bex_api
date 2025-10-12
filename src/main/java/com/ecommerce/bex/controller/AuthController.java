package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.AuthResponseDTO;
import com.ecommerce.bex.command.LoginCommand;
import com.ecommerce.bex.command.RegisterCommand;
import com.ecommerce.bex.service.SecurityService;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.AUTH_BASE_PATH)
public class AuthController {

    private final SecurityService securityService;

    @PostMapping(AppConstants.LOGIN_PATH)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginCommand dto){
        return ResponseEntity.ok(securityService.login(dto));
    }

    @PostMapping(AppConstants.REGISTER_PATH)
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterCommand dto){
        return ResponseEntity.ok(securityService.register(dto));
    }
}
