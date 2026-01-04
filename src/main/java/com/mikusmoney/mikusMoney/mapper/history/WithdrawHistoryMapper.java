package com.mikusmoney.mikusMoney.mapper.history;

import org.springframework.stereotype.Component;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.entity.Transaction;
import com.mikusmoney.mikusMoney.entity.Withdraw;

/**
 * Mapper for converting Withdraw transactions to TransactionHistoryResponse.
 */
@Component
public class WithdrawHistoryMapper implements TransactionHistoryMapper<Withdraw> {

    @Override
    public boolean supports(Transaction transaction) {
        return transaction instanceof Withdraw;
    }

    @Override
    public Class<Withdraw> getTransactionType() {
        return Withdraw.class;
    }

    @Override
    public TransactionHistoryResponse toHistoryResponse(Withdraw withdraw) {
        return TransactionHistoryResponse.builder()
                .id(withdraw.getId())
                .transactionType("WITHDRAW")
                .amount(withdraw.getAmount())
                .createdAt(withdraw.getCreatedAt())
                .owner(withdraw.getMiku().getFullName())
                .build();
    }
}
