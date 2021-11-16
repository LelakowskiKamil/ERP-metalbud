package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.converter.CustomerConverter;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public Customer saveCustomer(CreateCustomerCommand createCustomerCommand) {
        Customer customerToSave = customerConverter.from(createCustomerCommand);
        return customerRepository.save(customerToSave);
    }

    public List<Customer> getCustomers() {
     return customerRepository.findAll();
    }

}
