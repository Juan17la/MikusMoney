package com.mikusmoney.mikusMoney.services;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.accountDTOs.AccountResponse;
import com.mikusmoney.mikusMoney.entity.Account;
import com.mikusmoney.mikusMoney.validators.AuthValidator;
import com.mikusmoney.mikusMoney.validators.AuthValidator.AuthContext;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AuthValidator authValidator;

    @Transactional
    public AccountResponse getAccountDetail() {
        // Validate authentication (no PIN required for read-only operation)
        AuthContext context = authValidator.validateAuth(null);
        
        Account account = context.account();
        
        return AccountResponse.builder()
                .id(account.getId())
                .totalMoney(account.getTotalMoney())
                .fullName(context.miku().getFullName())
                .publicCode(context.miku().getPublicCode())
                .build();
    }
}
