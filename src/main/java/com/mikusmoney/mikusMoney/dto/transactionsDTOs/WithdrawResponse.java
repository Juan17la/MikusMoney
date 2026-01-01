package com.mikusmoney.mikusMoney.dto.transactionsDTOs;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawResponse {

    private Long id;
    private BigDecimal amount;
}
