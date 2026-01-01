package com.mikusmoney.mikusMoney.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikusmoney.mikusMoney.dto.accountDTOs.AccountResponse;
import com.mikusmoney.mikusMoney.services.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/v0.1/account/")
@RequiredArgsConstructor
public class AccountController {
    
    private final AccountService accountService;

    @GetMapping("details")
    public ResponseEntity<AccountResponse> getAccountDetails() {
        return ResponseEntity.ok(accountService.getAccountDetail());
    }
    
}
