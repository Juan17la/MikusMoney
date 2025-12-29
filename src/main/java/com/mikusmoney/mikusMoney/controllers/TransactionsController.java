package com.mikusmoney.mikusMoney.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikusmoney.mikusMoney.dto.DepositRequest;
import com.mikusmoney.mikusMoney.dto.DepositResponse;
import com.mikusmoney.mikusMoney.services.TransactionsService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/v0.1/transactions/")
@RequiredArgsConstructor
public class TransactionsController {
    
    private final TransactionsService transactionsService;

    @PostMapping("deposit")
    public ResponseEntity<DepositResponse> deposit(
        @RequestBody DepositRequest depositRequest,
        @RequestHeader("Idempotency-Key") String idempotencyKey
    ) {
        return ResponseEntity.ok(
            transactionsService.deposit(depositRequest, idempotencyKey)
        );
    }

}
