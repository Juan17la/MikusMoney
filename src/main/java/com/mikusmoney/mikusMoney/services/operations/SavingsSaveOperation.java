package com.mikusmoney.mikusMoney.services.operations;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigDepositRequest;
import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigResponse;
import com.mikusmoney.mikusMoney.entity.Miku;
import com.mikusmoney.mikusMoney.entity.SavingsPig;
import com.mikusmoney.mikusMoney.mapper.SavingsPigMapper;
import com.mikusmoney.mikusMoney.repository.SavingsPigRepository;
import com.mikusmoney.mikusMoney.services.AuthContextService;
import com.mikusmoney.mikusMoney.validations.SavingsValidations;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavingsSaveOperation implements SavingsOperation<SavingsPigDepositRequest, SavingsPigResponse> {

    private final AuthContextService authContextService;
    private final SavingsValidations savingsValidations;
    private final SavingsPigRepository savingsPigRepository;
    private final SavingsPigMapper savingsPigMapper;

    @Override
    public SavingsPigResponse execute(SavingsPigDepositRequest request) {
        
        // VALIDATE AUTH
        Miku miku = authContextService.getAuthenticatedMiku();
        
        // GET PIG
        SavingsPig savingsPig = savingsPigRepository.findByIdAndMikuId(request.getSavingsPigId(), miku.getId());

        //VALIDATIONS
        savingsValidations.transactionsValidation(request.getAmount(), savingsPig.getBroken());

        // PERFORM DEPOSIT
        savingsPig.setSavedMoney(savingsPig.getSavedMoney().add(request.getAmount()));
        savingsPigRepository.save(savingsPig);

        SavingsPig savedPig = savingsPigRepository.save(savingsPig);

        return savingsPigMapper.toResponse(savedPig);
    }
    
}
