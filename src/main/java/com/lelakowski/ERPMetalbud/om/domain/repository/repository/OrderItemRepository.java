package com.lelakowski.ERPMetalbud.om.domain.repository.repository;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<ProductOrderItem, Long> {
    @Query(value = "SELECT p FROM ProductOrderItem p WHERE p.id = ?1")
    ProductOrderItem getOne(Long id);
}