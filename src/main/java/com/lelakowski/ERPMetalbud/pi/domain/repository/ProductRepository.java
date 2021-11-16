package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}