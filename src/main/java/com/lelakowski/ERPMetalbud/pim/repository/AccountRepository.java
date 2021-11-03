package com.lelakowski.ERPMetalbud.pim.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}