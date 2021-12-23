package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Long> {

    @Query(value = "SELECT p FROM ProductSpecification p WHERE p.id = ?1")
    ProductSpecification getOne(Long id);

    @Query(value = "SELECT p.id FROM ProductSpecification p WHERE p.externalName = ?1")
    Optional<Long> findProductSpecificationIdByExternalName(String externalName);
}