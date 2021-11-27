package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomerBuilder {

    public Customer from(String name, String surname, Account account, Address address) {
//TODO Validation
        return Customer.builder()
                .name(name)
                .surname(surname)
                .account(account)
                .address(address)
                .productOrderList(new ArrayList<>())
                .build();
    }

}
