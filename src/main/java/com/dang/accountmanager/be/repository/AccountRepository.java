package com.dang.accountmanager.be.repository;

import com.dang.accountmanager.be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    boolean existsByUsername(String username);
}
