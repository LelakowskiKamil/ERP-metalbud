package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.converter.AddressConverter;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public void saveAddress(CreateAddressCommand createAddressCommand) {
        Address addressToSave = new AddressConverter().from(createAddressCommand);
        addressRepository.save(addressToSave);
    }

    public List<Address> getAddresses() {
     return addressRepository.findAll();
    }

}
