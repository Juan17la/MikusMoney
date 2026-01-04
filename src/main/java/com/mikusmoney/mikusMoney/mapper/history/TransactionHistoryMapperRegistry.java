package com.mikusmoney.mikusMoney.mapper.history;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.entity.Transaction;

import lombok.RequiredArgsConstructor;

/**
 * Registry that holds all TransactionHistoryMapper implementations.
 * Uses polymorphism to find and apply the correct mapper for each transaction type.
 * 
 * This class enables the Open/Closed Principle: to add a new transaction type,
 * simply create a new mapper implementing TransactionHistoryMapper - no modifications
 * to this class or the service are needed.
 */
@Component
@RequiredArgsConstructor
public class TransactionHistoryMapperRegistry {

    private final List<TransactionHistoryMapper<?>> mappers;

    /**
     * Maps a transaction to its history response using the appropriate mapper.
     * The correct mapper is selected at runtime based on the transaction type.
     * 
     * @param transaction The transaction to map
     * @return The mapped TransactionHistoryResponse
     * @throws ResponseStatusException if no mapper is found for the transaction type
     */
    @SuppressWarnings("unchecked")
    public TransactionHistoryResponse toHistoryResponse(Transaction transaction) {
        return mappers.stream()
                .filter(mapper -> mapper.supports(transaction))
                .findFirst()
                .map(mapper -> ((TransactionHistoryMapper<Transaction>) mapper).toHistoryResponse(transaction))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "No mapper found for transaction type: " + transaction.getClass().getSimpleName()
                ));
    }
}
