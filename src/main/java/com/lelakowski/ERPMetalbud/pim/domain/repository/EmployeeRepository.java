package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT p FROM Employee p WHERE p.id = ?1")
    Employee getOne(Long id);
}