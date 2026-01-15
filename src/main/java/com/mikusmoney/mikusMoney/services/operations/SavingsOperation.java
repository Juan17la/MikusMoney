package com.mikusmoney.mikusMoney.services.operations;

public interface SavingsOperation<RQ, RS> {
    RS execute(RQ request);
    
    // Optional override for operations that need pigId and idempotency key parameters
    default RS execute(Long pigId, RQ request, String idempotencyKey) {
        throw new UnsupportedOperationException("This operation does not support pigId and idempotency key parameters");
    }
} 
