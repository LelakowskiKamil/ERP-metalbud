package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.service.CustomerService;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List> getCustomers() {
        return new ResponseEntity(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/externalName/{externalName}")
    public ResponseEntity<List> customerIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity(customerService.getCustomerIdByExternalName(externalName), HttpStatus.OK);
    }

    @PostMapping(path = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerCommand createCustomerCommand) {
        customerService.saveCustomer(createCustomerCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
