package com.lelakowski.ERPMetalbud.pim.converter;

import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConverter {

    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;

    public Customer from(CreateCustomerCommand createCustomerCommand) {
        if (createCustomerCommand == null) throw new IllegalArgumentException();
        return Customer.builder()
                .name(createCustomerCommand.getName())
                .surname(createCustomerCommand.getSurname())
                .account(accountRepository.getOne(createCustomerCommand.getAccountId()))
                .address(addressRepository.getOne(createCustomerCommand.getAddressId()))
                .build();
    }

}
