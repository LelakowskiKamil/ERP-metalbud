package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.builder.AddressBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressBuilder addressBuilder;

    @Override
    public Long saveAddress(CreateAddressCommand createAddressCommand) {
        Address addressToSave = addressBuilder.from(
                createAddressCommand.getCity(),
                createAddressCommand.getPostalCode(),
                createAddressCommand.getState(),
                createAddressCommand.getCountry()
        );
        Address address = addressRepository.save(addressToSave);

        return address.getId();
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

}
