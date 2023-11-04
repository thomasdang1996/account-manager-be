package com.dang.accountmanager.be.mapper;

import com.dang.accountmanager.be.entity.Account;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        builder = @Builder(disableBuilder = true)
)
public interface AccountMapper {
    Account toAccountEntity(UUID id, String username, String password);
}
