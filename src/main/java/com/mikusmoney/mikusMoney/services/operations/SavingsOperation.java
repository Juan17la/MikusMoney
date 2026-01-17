package com.mikusmoney.mikusMoney.services.operations;

public interface SavingsOperation<RQ, RS> {
    RS execute(RQ request);
    
    // Optional override for operations that need pigId parameter
    default RS execute(Long pigId, RQ request) {
        throw new UnsupportedOperationException("This operation does not support pigId parameter");
    }
}