package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.sendDTOs.SendMoneyRequest;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.sendDTOs.SendResponse;
import com.mikusmoney.mikusMoney.entity.Send;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SendMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "sender", ignore = true) // Will be set in service
    @Mapping(target = "receiver", ignore = true) // Will be set in service
    Send toEntity(SendMoneyRequest request);

    SendResponse toResponse(Send send);
}
