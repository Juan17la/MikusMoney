package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.AccountResponse;
import com.mikusmoney.mikusMoney.entity.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    @Mapping(target = "mikuId", source = "miku.id")
    @Mapping(target = "mikuFullName", source = "miku.fullName")
    AccountResponse toResponse(Account account);
}
