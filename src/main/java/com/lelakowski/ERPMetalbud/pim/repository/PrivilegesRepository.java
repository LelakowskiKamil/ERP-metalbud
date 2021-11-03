package com.lelakowski.ERPMetalbud.pim.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegesRepository extends JpaRepository<Privileges, Long> {

}