package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;

import java.util.List;

public interface EmployeeService {

    Long saveEmployee(CreateEmployeeCommand createEmployeeCommand);

    List<Employee> getEmployees();
}
