package com.mikusmoney.mikusMoney.services.operations;

public interface SavingsOperation<RQ, RS> {
    RS execute(RQ request);
} 
