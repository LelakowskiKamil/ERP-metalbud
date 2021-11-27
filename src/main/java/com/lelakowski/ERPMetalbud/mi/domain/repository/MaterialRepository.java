package com.lelakowski.ERPMetalbud.mi.domain.repository;

import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

}