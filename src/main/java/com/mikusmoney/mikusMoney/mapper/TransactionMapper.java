package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.entity.Deposit;
import com.mikusmoney.mikusMoney.entity.Send;
import com.mikusmoney.mikusMoney.entity.Withdraw;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    
    // Convert Deposit to TransactionHistoryResponse
    @Mapping(target = "transactionType", constant = "DEPOSIT")
    @Mapping(target = "owner", source = "miku.fullName")
    @Mapping(target = "from", ignore = true)
    @Mapping(target = "to", ignore = true)
    TransactionHistoryResponse depositToHistory(Deposit deposit);
    
    // Convert Withdraw to TransactionHistoryResponse
    @Mapping(target = "transactionType", constant = "WITHDRAW")
    @Mapping(target = "owner", source = "miku.fullName")
    @Mapping(target = "from", ignore = true)
    @Mapping(target = "to", ignore = true)
    TransactionHistoryResponse withdrawToHistory(Withdraw withdraw);
    
    // Convert Send to TransactionHistoryResponse
    @Mapping(target = "transactionType", constant = "SEND")
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "from", source = "sender.fullName")
    @Mapping(target = "to", source = "receiver.fullName")
    TransactionHistoryResponse sendToHistory(Send send);
}
