package com.mikusmoney.mikusMoney.mapper;

import com.mikusmoney.mikusMoney.dto.MikuCreateRequest;
import com.mikusmoney.mikusMoney.entity.Credential;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CredentialMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "miku", ignore = true) // Will be set in service
    @Mapping(target = "password", ignore = true) // Will be hashed in service
    @Mapping(target = "pinCode", ignore = true) // Will be hashed in service
    Credential toEntity(MikuCreateRequest request);
}
