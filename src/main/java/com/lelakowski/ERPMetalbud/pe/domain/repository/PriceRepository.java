package com.lelakowski.ERPMetalbud.pe.domain.repository;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}