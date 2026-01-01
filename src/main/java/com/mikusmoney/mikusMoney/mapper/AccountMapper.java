package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.accountDTOs.AccountResponse;
import com.mikusmoney.mikusMoney.entity.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    
    AccountResponse toResponse(Account account);
}
