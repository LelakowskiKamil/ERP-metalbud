package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository customerRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
     return employeeRepository.findAll();
    }

}
