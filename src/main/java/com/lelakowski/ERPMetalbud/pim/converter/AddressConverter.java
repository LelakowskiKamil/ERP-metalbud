package com.lelakowski.ERPMetalbud.pim.converter;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address from(CreateAddressCommand createAddressCommand) {
        if (createAddressCommand == null) throw new IllegalArgumentException();
        return Address.builder()
                .city(createAddressCommand.getCity())
                .postalCode(createAddressCommand.getPostalCode())
                .state(createAddressCommand.getState())
                .country(createAddressCommand.getCountry())
                .build();
    }

}
