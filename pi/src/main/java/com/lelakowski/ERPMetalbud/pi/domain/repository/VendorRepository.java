package com.lelakowski.ERPMetalbud.pi.domain.repository;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query(value = "SELECT v FROM Vendor v WHERE v.id = ?1")
    Vendor getOne(Long id);
}