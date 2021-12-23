package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT p FROM Account p WHERE p.id = ?1")
    Account getOne(Long id);

    @Query(value = "SELECT a.id FROM Account a WHERE a.externalName = ?1")
    Optional<Long> findAccountIdByCaption(String externalName);
}