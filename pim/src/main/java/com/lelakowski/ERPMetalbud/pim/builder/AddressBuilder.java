package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressBuilder {

    public Address from(String city, String postalCode, String state, String country) {
        return Address.builder()
                .city(city)
                .postalCode(postalCode)
                .state(state)
                .country(country)
                .build();
    }

}
