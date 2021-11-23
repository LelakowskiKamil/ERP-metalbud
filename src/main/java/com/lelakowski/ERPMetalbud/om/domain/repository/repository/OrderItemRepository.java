package com.lelakowski.ERPMetalbud.om.domain.repository.repository;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<ProductOrderItem, Long> {

}