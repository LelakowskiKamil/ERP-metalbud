package com.lelakowski.ERPMetalbud.pim.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}