package com.mikusmoney.mikusMoney.services.operations;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigBreakRequest;
import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigResponse;
import com.mikusmoney.mikusMoney.entity.SavingsPig;
import com.mikusmoney.mikusMoney.mapper.SavingsPigMapper;
import com.mikusmoney.mikusMoney.repository.AccountRepository;
import com.mikusmoney.mikusMoney.repository.SavingsPigRepository;
import com.mikusmoney.mikusMoney.services.AuthContextService;
import com.mikusmoney.mikusMoney.services.AuthContextService.AuthContext;
import com.mikusmoney.mikusMoney.validations.SavingsValidations;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakSavingsOperation implements SavingsOperation<SavingsPigBreakRequest, SavingsPigResponse>{
    
    private final SavingsValidations savingsValidations;
    private final SavingsPigRepository savingsPigRepository;
    private final AuthContextService authContextService;
    private final SavingsPigMapper savingsPigMapper;
    private final AccountRepository accountRepository;

    @Override
    public SavingsPigResponse execute(SavingsPigBreakRequest request) {
        throw new UnsupportedOperationException("Use execute with pigId and idempotency key");
    }

    @Override
    public SavingsPigResponse execute(Long pigId, SavingsPigBreakRequest request) {

        // Validate authentication and PIN
        AuthContext context = authContextService.validateAuth(request.getPinCode());

        SavingsPig savingsPig = savingsPigRepository.findByIdAndMikuId(pigId, context.miku().getId());
        
        savingsValidations.validateBrokenPig(savingsPig.getBroken());

        savingsPig.setBroken(true);
        savingsPig.setBrokenAt(LocalDateTime.now());

        context.account().deposit(savingsPig.getSavedMoney());
        accountRepository.save(context.account());
        
        SavingsPig brokenPig = savingsPigRepository.save(savingsPig);

        return savingsPigMapper.toResponse(brokenPig);
    }
    
}