package com.mikusmoney.mikusMoney.validations;

import java.math.BigDecimal;

import com.mikusmoney.mikusMoney.repository.SavingsPigRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SavingsValidations {

    private final SavingsPigRepository savingsPigRepository;

    public Boolean transactionsValidation(BigDecimal amount, Boolean isBroken) {
        validateBrokenPig(isBroken);
        validateDepositAmount(amount);
        return true;
    }

    public Boolean createPigValidation(Long mikuId, BigDecimal goal) {
        validateMaxUnbrokenPigs(mikuId);
        validateGoalAmount(goal);
        return true;
    }

    // Single Validations

    private void validateDepositAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
    }

    private void validateBrokenPig(Boolean isBroken) {
        if (isBroken) {
            throw new IllegalStateException("Cannot perform this operation on a broken savings pig.");
        }
    }

    private void validateMaxUnbrokenPigs(Long mikuId) {
        int numberOfPigs = savingsPigRepository.getNumberOfPigsByOwnerId(mikuId);
        
        if(numberOfPigs >= 5) {
            throw new IllegalStateException("You cannot have more than 5 unbroken savings pigs.");
        }
        
    }

    private void validateGoalAmount(BigDecimal goal) {
        if (goal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Goal amount must be greater than zero.");
        }
    }
    
}
