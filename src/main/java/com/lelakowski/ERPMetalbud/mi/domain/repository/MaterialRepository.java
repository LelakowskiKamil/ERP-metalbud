package com.lelakowski.ERPMetalbud.mi.domain.repository;

import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query(value = "SELECT p FROM Material p WHERE p.id = ?1")
    Material getOne(Long id);
}