package com.mikusmoney.mikusMoney.dto.savingsDTOs;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsPigResponse {

    private Long id;
    private BigDecimal savedMoney;
    private Boolean broken;
    private LocalDateTime brokenAt;
    private LocalDateTime createdAt;
    private Long mikuId;
}
