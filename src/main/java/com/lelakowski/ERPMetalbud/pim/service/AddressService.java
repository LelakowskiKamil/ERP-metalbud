package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAddresses() {
     return addressRepository.findAll();
    }

}
