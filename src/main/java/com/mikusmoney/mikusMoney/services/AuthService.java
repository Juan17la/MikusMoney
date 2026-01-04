package com.mikusmoney.mikusMoney.services;

import com.mikusmoney.mikusMoney.dto.authenticationDTOs.AuthResponse;
import com.mikusmoney.mikusMoney.dto.authenticationDTOs.LoginRequest;
import com.mikusmoney.mikusMoney.dto.mikuDTOs.MikuCreateRequest;
import com.mikusmoney.mikusMoney.dto.mikuDTOs.MikuResponse;
import com.mikusmoney.mikusMoney.entity.Miku;
import com.mikusmoney.mikusMoney.mapper.MikuMapper;
import com.mikusmoney.mikusMoney.services.operations.LoginOperation;
import com.mikusmoney.mikusMoney.services.operations.SignUpOperation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MikuMapper mikuMapper;
    
    private final SignUpOperation signUpOperation;
    private final LoginOperation loginOperation;

    @Transactional
    public AuthResponse signUp(MikuCreateRequest request) {
        return signUpOperation.execute(request);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        return loginOperation.execute(request);
    }

    public MikuResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        Miku miku = (Miku) authentication.getPrincipal();
        return mikuMapper.toResponse(miku);
    }

}

