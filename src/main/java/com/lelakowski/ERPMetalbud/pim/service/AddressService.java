package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;

import java.util.List;

public interface AddressService {

    Long saveAddress(CreateAddressCommand createAddressCommand);

    List<Address> getAddresses();
}
