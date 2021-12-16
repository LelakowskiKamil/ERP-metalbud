package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.VendorBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorBuilder vendorBuilder;

    @Override
    public Long saveVendor(CreateVendorCommand createVendorCommand) {
        Vendor vendorToSave = vendorBuilder.from(createVendorCommand.getCaption());
        Vendor vendor = vendorRepository.save(vendorToSave);
        return vendor.getId();
    }

    @Override
    public List<Vendor> getVendors() {
        return vendorRepository.findAll();
    }
}
