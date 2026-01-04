package com.mikusmoney.mikusMoney.mapper.history;

import org.springframework.stereotype.Component;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.entity.Deposit;
import com.mikusmoney.mikusMoney.entity.Transaction;

/**
 * Mapper for converting Deposit transactions to TransactionHistoryResponse.
 */
@Component
public class DepositHistoryMapper implements TransactionHistoryMapper<Deposit> {

    @Override
    public boolean supports(Transaction transaction) {
        return transaction instanceof Deposit;
    }

    @Override
    public Class<Deposit> getTransactionType() {
        return Deposit.class;
    }

    @Override
    public TransactionHistoryResponse toHistoryResponse(Deposit deposit) {
        return TransactionHistoryResponse.builder()
                .id(deposit.getId())
                .transactionType("DEPOSIT")
                .amount(deposit.getAmount())
                .createdAt(deposit.getCreatedAt())
                .owner(deposit.getMiku().getFullName())
                .build();
    }
}
