package com.mikusmoney.mikusMoney.services;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigCreationRequest;
import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigResponse;
import com.mikusmoney.mikusMoney.services.operations.CreateSavingsPigOperation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavingsPigService {
    

    private final CreateSavingsPigOperation createSavingsPig;
    
    public SavingsPigResponse createSavingsPig(SavingsPigCreationRequest request) {
        return createSavingsPig.execute(request);
    }

}
