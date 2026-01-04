package com.mikusmoney.mikusMoney.mapper.history;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.entity.Transaction;

/**
 * Strategy interface for mapping different transaction types to TransactionHistoryResponse.
 * Each transaction type (Deposit, Withdraw, Send) should have its own implementation.
 * 
 * @param <T> The specific transaction type this mapper handles
 */
public interface TransactionHistoryMapper<T extends Transaction> {
    
    /**
     * Checks if this mapper supports the given transaction type.
     * 
     * @param transaction The transaction to check
     * @return true if this mapper can handle the transaction type
     */
    boolean supports(Transaction transaction);
    
    /**
     * Returns the transaction class this mapper handles.
     * Used for runtime type resolution.
     * 
     * @return The class of the transaction type
     */
    Class<T> getTransactionType();
    
    /**
     * Maps the transaction to a TransactionHistoryResponse.
     * 
     * @param transaction The transaction to map
     * @return The mapped TransactionHistoryResponse
     */
    TransactionHistoryResponse toHistoryResponse(T transaction);
}
