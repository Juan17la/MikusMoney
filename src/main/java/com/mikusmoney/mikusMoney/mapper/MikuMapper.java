package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.mikuDTOs.MikuCreateRequest;
import com.mikusmoney.mikusMoney.dto.mikuDTOs.MikuResponse;
import com.mikusmoney.mikusMoney.entity.Miku;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MikuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicCode", ignore = true) // Will be generated in service
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "credential", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "savingsPigs", ignore = true)
    Miku toEntity(MikuCreateRequest request);

    @Mapping(target = "email", source = "credential.email")
    @Mapping(target = "phoneNumber", source = "credential.phoneNumber")
    MikuResponse toResponse(Miku miku);
}
