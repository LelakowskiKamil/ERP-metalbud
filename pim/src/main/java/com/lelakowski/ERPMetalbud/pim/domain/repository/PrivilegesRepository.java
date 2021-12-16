package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegesRepository extends JpaRepository<Privileges, Long> {
    @Query(value = "SELECT p FROM Privileges p WHERE p.id = ?1")
    Privileges getOne(Long id);
}