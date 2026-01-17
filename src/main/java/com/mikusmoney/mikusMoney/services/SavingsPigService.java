package com.mikusmoney.mikusMoney.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigBreakRequest;
import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigCreationRequest;
import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigDepositRequest;
import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigResponse;
import com.mikusmoney.mikusMoney.services.operations.BreakSavingsOperation;
import com.mikusmoney.mikusMoney.services.operations.CreateSavingsPigOperation;
import com.mikusmoney.mikusMoney.services.operations.GetSavingsPigsOperation;
import com.mikusmoney.mikusMoney.services.operations.SavingsSaveOperation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavingsPigService {

    private final CreateSavingsPigOperation createSavingsPigOperation;
    private final GetSavingsPigsOperation getSavingsPigsOperation;
    private final SavingsSaveOperation savingsDepositOperation;
    private final BreakSavingsOperation breakSavingsOperation;

    public SavingsPigResponse createSavingsPig(SavingsPigCreationRequest request) {
        return createSavingsPigOperation.execute(request);
    }

    @Transactional
    public SavingsPigResponse brakeSavingsPig(Long pigId, SavingsPigBreakRequest request) {
        return breakSavingsOperation.execute(pigId, request);
    }

    @Transactional
    public SavingsPigResponse depositMoney(Long pigId, SavingsPigDepositRequest request) {
        return savingsDepositOperation.execute(pigId, request);
    }

    public List<SavingsPigResponse> getSavingsPigs() {
        return getSavingsPigsOperation.execute(null);
    }
}
