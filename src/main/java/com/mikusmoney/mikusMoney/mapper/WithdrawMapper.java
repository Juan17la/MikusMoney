package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.WithdrawResponse;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.WithdrawRequest;
import com.mikusmoney.mikusMoney.entity.Withdraw;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WithdrawMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "miku", ignore = true) // Will be set in service
    Withdraw toEntity(WithdrawRequest request);

    WithdrawResponse toResponse(Withdraw withdraw);
}
