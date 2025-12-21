package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.entity.Deposit;
import com.mikusmoney.mikusMoney.entity.Send;
import com.mikusmoney.mikusMoney.entity.Withdraw;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mapping(target = "mikuId", source = "miku.id")
    @Mapping(target = "mikuFullName", source = "miku.fullName")
    @Mapping(target = "senderMikuId", ignore = true)
    @Mapping(target = "senderFullName", ignore = true)
    @Mapping(target = "receiverMikuId", ignore = true)
    @Mapping(target = "receiverFullName", ignore = true)
    TransactionHistoryResponse depositToHistoryResponse(Deposit deposit);

    @Mapping(target = "mikuId", source = "miku.id")
    @Mapping(target = "mikuFullName", source = "miku.fullName")
    @Mapping(target = "senderMikuId", ignore = true)
    @Mapping(target = "senderFullName", ignore = true)
    @Mapping(target = "receiverMikuId", ignore = true)
    @Mapping(target = "receiverFullName", ignore = true)
    TransactionHistoryResponse withdrawToHistoryResponse(Withdraw withdraw);

    @Mapping(target = "mikuId", ignore = true)
    @Mapping(target = "mikuFullName", ignore = true)
    @Mapping(target = "senderMikuId", source = "sender.id")
    @Mapping(target = "senderFullName", source = "sender.fullName")
    @Mapping(target = "receiverMikuId", source = "receiver.id")
    @Mapping(target = "receiverFullName", source = "receiver.fullName")
    TransactionHistoryResponse sendToHistoryResponse(Send send);
}
