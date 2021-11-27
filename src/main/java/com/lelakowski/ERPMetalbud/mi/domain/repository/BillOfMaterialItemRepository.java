package com.lelakowski.ERPMetalbud.mi.domain.repository;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillOfMaterialItemRepository extends JpaRepository<BillOfMaterialItem, Long> {

}