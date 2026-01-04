package com.mikusmoney.mikusMoney.mapper.history;

import org.springframework.stereotype.Component;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.entity.Send;
import com.mikusmoney.mikusMoney.entity.Transaction;

/**
 * Mapper for converting Send transactions to TransactionHistoryResponse.
 */
@Component
public class SendHistoryMapper implements TransactionHistoryMapper<Send> {

    @Override
    public boolean supports(Transaction transaction) {
        return transaction instanceof Send;
    }

    @Override
    public Class<Send> getTransactionType() {
        return Send.class;
    }

    @Override
    public TransactionHistoryResponse toHistoryResponse(Send send) {
        return TransactionHistoryResponse.builder()
                .id(send.getId())
                .transactionType("SEND")
                .amount(send.getAmount())
                .createdAt(send.getCreatedAt())
                .from(send.getSender().getFullName())
                .to(send.getReceiver().getFullName())
                .build();
    }
}
