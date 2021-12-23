package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;

import java.util.List;

public interface VendorService {

    Long saveVendor(CreateVendorCommand createVendorCommand);

    List<Vendor> getVendors();

    Long getVendorIdByExternalName(String externalName);

}
