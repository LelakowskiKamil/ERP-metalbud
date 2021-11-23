package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.conventer.BrandConverter;
import com.lelakowski.ERPMetalbud.pi.conventer.VendorConverter;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.domain.repository.BrandRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandConverter brandConverter;


    @Override
    public void saveBrand(CreateBrandCommand createBrandCommand) {
        Brand brandToSave = brandConverter.from(createBrandCommand);
        brandRepository.save(brandToSave);
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }
}
