package com.lelakowski.ERPMetalbud.om.domain.repository.repository;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<ProductOrderItem, Long> {
    @Query(value = "SELECT p FROM ProductOrderItem p WHERE p.id = ?1")
    @NotNull
    ProductOrderItem getOne(@NotNull Long id);

    @Query(value = "SELECT p FROM ProductOrderItem p WHERE p.productOrder.id = ?1")
    List<ProductOrderItem> findAllByOrderId(@NotNull Long id);
}