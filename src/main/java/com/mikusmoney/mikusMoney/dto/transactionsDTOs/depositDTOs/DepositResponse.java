package com.mikusmoney.mikusMoney.dto.transactionsDTOs.depositDTOs;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepositResponse {

    private Long id;
    private BigDecimal amount;
}
