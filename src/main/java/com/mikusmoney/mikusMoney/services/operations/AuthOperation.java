package com.mikusmoney.mikusMoney.services.operations;

public interface AuthOperation<RQ, RS> {
    RS execute(RQ request);
}
