package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;

import java.util.List;

public interface CustomerService {

     Long saveCustomer(CreateCustomerCommand createCustomerCommand);

     List<Customer> getCustomers();
}
