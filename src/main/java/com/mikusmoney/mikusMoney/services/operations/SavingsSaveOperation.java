package com.mikusmoney.mikusMoney.services.operations;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigDepositRequest;
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
public class SavingsSaveOperation implements SavingsOperation<SavingsPigDepositRequest, SavingsPigResponse> {

    private final AuthContextService authContextService;
    private final SavingsValidations savingsValidations;
    private final SavingsPigRepository savingsPigRepository;
    private final SavingsPigMapper savingsPigMapper;
    private final AccountRepository accountRepository;

    @Override
    public SavingsPigResponse execute(SavingsPigDepositRequest request) {
        throw new UnsupportedOperationException("Use execute with pigId and idempotency key");
    }

    @Override
    public SavingsPigResponse execute(Long pigId, SavingsPigDepositRequest request) {
        
        // Validate authentication and PIN
        AuthContext context = authContextService.validateAuth(request.getPinCode());

        SavingsPig savingsPig = savingsPigRepository.findByIdAndMikuId(pigId, context.miku().getId());

        // VALIDATIONS
        savingsValidations.transactionsValidation(request.getAmount(), savingsPig.getBroken(), context.account().getTotalMoney());

        // PERFORM DEPOSIT
        context.account().withdraw(request.getAmount());
        accountRepository.save(context.account());

        savingsPig.setSavedMoney(savingsPig.getSavedMoney().add(request.getAmount()));
        
        savingsPigRepository.save(savingsPig);

        SavingsPig savedPig = savingsPigRepository.save(savingsPig);

        return savingsPigMapper.toResponse(savedPig);
    }
    
}
