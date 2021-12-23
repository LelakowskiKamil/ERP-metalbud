package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE p.id = ?1")
    Product getOne(Long id);

    @Query(value = "SELECT p.id FROM Product p WHERE p.externalName = ?1")
    Optional<Long> findProductIdByExternalName(String externalName);
}