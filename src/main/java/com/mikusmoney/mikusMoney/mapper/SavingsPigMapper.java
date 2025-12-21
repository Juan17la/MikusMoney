package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.SavingsPigResponse;
import com.mikusmoney.mikusMoney.entity.SavingsPig;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavingsPigMapper {

    @Mapping(target = "mikuId", source = "miku.id")
    @Mapping(target = "mikuFullName", source = "miku.fullName")
    SavingsPigResponse toResponse(SavingsPig savingsPig);
}
