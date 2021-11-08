package com.lelakowski.ERPMetalbud.pim.converter;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConverter {

    public Customer from(CreateCustomerCommand createCustomerCommand) {
        if (createCustomerCommand == null) throw new IllegalArgumentException();
        return Customer.builder()
                .name(createCustomerCommand.getName())
                .surname(createCustomerCommand.getSurname())
                .account(createCustomerCommand.getAccount())
                .address(createCustomerCommand.getAddress())
                .build();
    }

}
