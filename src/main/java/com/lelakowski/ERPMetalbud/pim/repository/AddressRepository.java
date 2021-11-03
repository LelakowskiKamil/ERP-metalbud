package com.lelakowski.ERPMetalbud.pim.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}