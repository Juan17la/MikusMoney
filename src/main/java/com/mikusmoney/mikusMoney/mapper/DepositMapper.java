package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.DepositRequest;
import com.mikusmoney.mikusMoney.dto.DepositResponse;
import com.mikusmoney.mikusMoney.entity.Deposit;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepositMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "transactionType", ignore = true)
    @Mapping(target = "miku", ignore = true) // Will be set in service
    Deposit toEntity(DepositRequest request);

    @Mapping(target = "mikuId", source = "miku.id")
    @Mapping(target = "mikuFullName", source = "miku.fullName")
    DepositResponse toResponse(Deposit deposit);
}
