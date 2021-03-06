package com.lelakowski.ERPMetalbud.om.domain.repository.repository;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query(value = "SELECT o FROM ProductOrder o WHERE o.customerId = ?1")
    List<ProductOrder> getOrdersForCustomer(Long customerId);

    @Query(value = "SELECT p FROM ProductOrder p WHERE p.id = ?1")
    @NotNull
    ProductOrder getOne(@NotNull Long id);
}