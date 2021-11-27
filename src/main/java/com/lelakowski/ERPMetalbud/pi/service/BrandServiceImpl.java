package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.BrandBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.repository.BrandRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandBuilder brandBuilder;

    @Override
    public Long saveBrand(CreateBrandCommand createBrandCommand) {
        Brand brandToSave = brandBuilder.from(createBrandCommand.getCaption());
        Brand brand = brandRepository.save(brandToSave);
        return brand.getId();
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }
}
