package com.mikusmoney.mikusMoney.dto.savingsDTOs;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsPigDepositRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Digits(integer = 17, fraction = 2, message = "Invalid amount format")
    private BigDecimal amount;

    @NotNull(message = "Savings Pig ID is required")
    @Positive(message = "Savings Pig ID must be a positive number")
    private Long savingsPigId;

}
