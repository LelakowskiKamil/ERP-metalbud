package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT b FROM Brand b WHERE b.id = ?1")
    Brand getOne(Long id);

    @Query(value = "SELECT b.id FROM Brand b WHERE b.externalName = ?1")
    Optional<Long> findBrandByExternalName(String externalName);

}