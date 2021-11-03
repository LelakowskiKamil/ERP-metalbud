package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List> getEmployees() {
        return new ResponseEntity(employeeService.getEmployees(), HttpStatus.OK);
    }

    @PostMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> createEmployee(@RequestBody Employee toCreate) {
        employeeService.saveEmployee(toCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
