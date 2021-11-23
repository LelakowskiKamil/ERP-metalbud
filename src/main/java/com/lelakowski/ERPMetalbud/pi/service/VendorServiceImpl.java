package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.conventer.VendorConverter;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.service.VendorService;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
import com.lelakowski.ERPMetalbud.pim.converter.AddressConverter;
import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.service.AddressService;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorConverter vendorConverter;

    @Override
    public void saveVendor(CreateVendorCommand createVendorCommand) {
        Vendor vendorToSave = vendorConverter.from(createVendorCommand);
        vendorRepository.save(vendorToSave);
    }

    @Override
    public List<Vendor> getVendors() {
        return vendorRepository.findAll();
    }
}
