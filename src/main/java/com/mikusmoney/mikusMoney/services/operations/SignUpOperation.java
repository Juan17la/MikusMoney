package com.mikusmoney.mikusMoney.services.operations;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mikusmoney.mikusMoney.dto.authenticationDTOs.AuthResponse;
import com.mikusmoney.mikusMoney.dto.mikuDTOs.MikuCreateRequest;
import com.mikusmoney.mikusMoney.entity.Account;
import com.mikusmoney.mikusMoney.entity.Credential;
import com.mikusmoney.mikusMoney.entity.Miku;
import com.mikusmoney.mikusMoney.mapper.MikuMapper;
import com.mikusmoney.mikusMoney.repository.AccountRepository;
import com.mikusmoney.mikusMoney.repository.CredentialRepository;
import com.mikusmoney.mikusMoney.repository.MikuRepository;
import com.mikusmoney.mikusMoney.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpOperation implements AuthOperation<MikuCreateRequest, AuthResponse> {

    private final MikuMapper mikuMapper;
    private final MikuRepository mikuRepository;
    private final CredentialRepository credentialRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse execute(MikuCreateRequest request) {
        // Validate unique constraints
        if (credentialRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        
        if (credentialRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }
        
        // Validate password confirmation
        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }

        // Validate PIN confirmation
        if (!request.getPinCode().equals(request.getPinCodeConfirmation())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PIN codes do not match");
        }
        
        // Create and validate Miku entity
        Miku miku = mikuMapper.toEntity(request);
        
        if (!miku.isAdult()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User must be at least 18 years old to register");
        }
        
        // Persist Miku
        Miku savedMiku = mikuRepository.save(miku);
        
        // Create and persist Credential
        Credential credential = Credential.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .pinCode(passwordEncoder.encode(request.getPinCode()))
                .miku(savedMiku)
                .build();
        
        Credential savedCredential = credentialRepository.save(credential);
        
        // Create and persist Account
        Account account = Account.builder()
                .totalMoney(BigDecimal.ZERO)
                .miku(savedMiku)
                .build();
        
        accountRepository.save(account);
        
        // Link entities
        savedMiku.setCredential(savedCredential);
        savedMiku.setAccount(account);
        
        return AuthResponse.builder()
                .token(jwtService.getToken(savedMiku))
                .build();
    }
}
