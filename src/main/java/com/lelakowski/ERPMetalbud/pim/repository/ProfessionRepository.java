package com.lelakowski.ERPMetalbud.pim.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {

}
