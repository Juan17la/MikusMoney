package com.mikusmoney.mikusMoney.services.operations;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mikusmoney.mikusMoney.dto.authenticationDTOs.AuthResponse;
import com.mikusmoney.mikusMoney.dto.authenticationDTOs.LoginRequest;
import com.mikusmoney.mikusMoney.entity.Credential;
import com.mikusmoney.mikusMoney.entity.Miku;
import com.mikusmoney.mikusMoney.repository.CredentialRepository;
import com.mikusmoney.mikusMoney.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginOperation implements AuthOperation<LoginRequest, AuthResponse> {

    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse execute(LoginRequest request) {
        // Find credential by email
        Credential credential = credentialRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        
        // Validate PIN code
        if (!passwordEncoder.matches(request.getPinCode(), credential.getPinCode())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid PIN code");
        }
        
        Miku miku = credential.getMiku();
        
        return AuthResponse.builder()
                .token(jwtService.getToken(miku))
                .build();
    }
}
