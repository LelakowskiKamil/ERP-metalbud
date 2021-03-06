package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerBuilder {

    public Customer from(String externalName, String name, String surname, Account account, Address address) {
        return Customer.builder()
                .externalName(externalName)
                .name(name)
                .surname(surname)
                .account(account)
                .address(address)
                .build();
    }

}
