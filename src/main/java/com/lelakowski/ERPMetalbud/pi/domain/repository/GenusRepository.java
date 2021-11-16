package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Genus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenusRepository extends JpaRepository<Genus, Long> {

}