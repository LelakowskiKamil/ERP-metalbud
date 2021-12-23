package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.builder.CustomerBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import com.lelakowski.ERPMetalbud.pim.notification.NotFoundCustomerWithExternalNameException;
import com.lelakowski.ERPMetalbud.pim.validation.CreateCustomerValidator;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBuilder customerBuilder;
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    private final CreateCustomerValidator createCustomerValidator;

    @Transactional
    @Override
    public Long saveCustomer(CreateCustomerCommand createCustomerCommand) {
        createCustomerValidator.validate(createCustomerCommand);

        Account account = accountRepository.getOne(createCustomerCommand.getAccountId());
        Address address = addressRepository.getOne(createCustomerCommand.getAddressId());

        Customer customerToSave = customerBuilder.from(
                createCustomerCommand.getExternalName(),
                createCustomerCommand.getName(),
                createCustomerCommand.getSurname(),
                account,
                address
        );

        Customer customer = customerRepository.save(customerToSave);
        return customer.getId();
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Long getCustomerIdByExternalName(String externalName) {
        Optional<Long> customerIdOpt = customerRepository.findCustomerIdByCaption(externalName);
        if (customerIdOpt.isEmpty()) throw new NotFoundCustomerWithExternalNameException(externalName);
        return customerIdOpt.get();
    }

}
