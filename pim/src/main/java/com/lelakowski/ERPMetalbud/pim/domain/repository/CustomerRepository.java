package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT c FROM Customer c WHERE c.id = ?1")
    Customer getOne(Long id);

    @Query(value = "SELECT c.id FROM Customer c WHERE c.externalName = ?1")
    Optional<Long> findCustomerIdByCaption(String externalName);
}