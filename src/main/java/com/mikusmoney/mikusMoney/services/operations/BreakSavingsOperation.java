package com.mikusmoney.mikusMoney.services.operations;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigBreakRequest;
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
public class BreakSavingsOperation implements SavingsOperation<SavingsPigBreakRequest, SavingsPigResponse>{
    
    private final SavingsValidations savingsValidations;
    private final SavingsPigRepository savingsPigRepository;
    private final AuthContextService authContextService;
    private final SavingsPigMapper savingsPigMapper;

    @Override
    public SavingsPigResponse execute(SavingsPigBreakRequest request) {

        Miku miku = authContextService.getAuthenticatedMiku();

        SavingsPig pig = savingsPigRepository.findByIdAndMikuId(request.getSavingsPigId(), miku.getId());
        
        savingsValidations.validateBrokenPig(pig.getBroken());

        pig.setBroken(true);
        pig.setBrokenAt(LocalDateTime.now());
        
        SavingsPig brokenPig = savingsPigRepository.save(pig);

        return savingsPigMapper.toResponse(brokenPig);
        
    }
    
}
