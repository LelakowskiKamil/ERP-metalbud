package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.domain.model.Employee;
import com.lelakowski.ERPMetalbud.pim.service.EmployeeService;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List> getEmployees() {
        return new ResponseEntity(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/externalName/{externalName}")
    public ResponseEntity<List> employeeIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity(employeeService.getEmployeeIdByExternalName(externalName), HttpStatus.OK);
    }


    @PostMapping(path = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Employee> createEmployee(@RequestBody @Valid CreateEmployeeCommand createEmployeeCommand) {
        employeeService.saveEmployee(createEmployeeCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
