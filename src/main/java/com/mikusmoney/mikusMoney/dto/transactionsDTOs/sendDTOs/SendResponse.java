package com.mikusmoney.mikusMoney.dto.transactionsDTOs.sendDTOs;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendResponse {

    private Long id;
    private BigDecimal amount;
}
