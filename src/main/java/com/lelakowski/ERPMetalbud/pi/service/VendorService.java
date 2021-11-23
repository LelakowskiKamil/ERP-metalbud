package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;

import java.util.List;

public interface VendorService {

     void saveVendor(CreateVendorCommand createVendorCommand);

     List<Vendor> getVendors();
}
