package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.converter.EmployeeConverter;
import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.domain.repository.EmployeeRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee saveEmployee(CreateEmployeeCommand createEmployeeCommand) {
        Employee employeeToSave = new EmployeeConverter().from(createEmployeeCommand);
        return employeeRepository.save(employeeToSave);
    }

    public List<Employee> getEmployees() {
     return employeeRepository.findAll();
    }

}
