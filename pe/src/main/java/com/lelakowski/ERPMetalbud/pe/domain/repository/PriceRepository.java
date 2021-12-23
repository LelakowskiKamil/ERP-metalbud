package com.lelakowski.ERPMetalbud.pe.domain.repository;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "SELECT p FROM Price p WHERE p.id = ?1")
    Price getOne(Long id);

    @Query(value = "SELECT p.id FROM Price p WHERE p.externalName = ?1")
    Optional<Long> getPriceByExternalName(String externalName);
}