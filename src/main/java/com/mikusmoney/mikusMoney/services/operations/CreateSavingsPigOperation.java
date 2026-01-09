package com.mikusmoney.mikusMoney.services.operations;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigCreationRequest;
import com.mikusmoney.mikusMoney.dto.savingsDTOs.SavingsPigResponse;
import com.mikusmoney.mikusMoney.entity.Miku;
import com.mikusmoney.mikusMoney.entity.SavingsPig;
import com.mikusmoney.mikusMoney.mapper.SavingsPigMapper;
import com.mikusmoney.mikusMoney.repository.SavingsPigRepository;
import com.mikusmoney.mikusMoney.services.AuthContextService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSavingsPigOperation implements SavingsOperation<SavingsPigCreationRequest, SavingsPigResponse> {
    
    private final AuthContextService authContextService;
    private final SavingsPigRepository savingsPigRepository;
    private final SavingsPigMapper savingsPigMapper;
    
    @Override
    public SavingsPigResponse execute(SavingsPigCreationRequest request) {
        
        // VAlIDATE AUTH
        Miku miku = authContextService.getAuthenticatedMiku();

        // VALIDATE LESS THAN 5 NOT BROKEN PIGS
        
        int numberOfPigs = savingsPigRepository.getNumberOfPigsByOwnerId(miku.getId());
        
        if(numberOfPigs >= 5) {
            throw new IllegalStateException("You cannot have more than 5 unbroken savings pigs.");
        }
        
        // VALIDATE REQUEST FIELDS
        if(request.getGoal() == null || request.getGoal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Goal amount must be greater than zero.");
        }

        SavingsPig savingsPig = savingsPigMapper.toEntity(request);
        savingsPig.setMiku(miku);
        SavingsPig savedPig = savingsPigRepository.save(savingsPig);
        
        return savingsPigMapper.toResponse(savedPig);
    }
    
}
