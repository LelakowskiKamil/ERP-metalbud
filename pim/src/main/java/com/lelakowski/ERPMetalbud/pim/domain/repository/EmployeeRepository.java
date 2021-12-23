package com.lelakowski.ERPMetalbud.pim.domain.repository;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT e FROM Employee e WHERE e.id = ?1")
    Employee getOne(Long id);

    @Query(value = "SELECT e.id FROM Employee e WHERE e.externalName = ?1")
    Optional<Long> findEmployeeIdByCaption(String externalName);
}