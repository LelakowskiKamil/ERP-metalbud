package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}