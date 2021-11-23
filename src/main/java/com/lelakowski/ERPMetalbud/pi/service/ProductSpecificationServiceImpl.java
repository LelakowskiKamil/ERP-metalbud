package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.conventer.BrandConverter;
import com.lelakowski.ERPMetalbud.pi.conventer.ProductSpecificationConverter;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.domain.repository.BrandRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    private final ProductSpecificationRepository productSpecificationRepository;
    private final ProductSpecificationConverter productSpecificationConverter;


    @Override
    public void saveProductSpecification(CreateProductSpecification createProductSpecification) {
ProductSpecification productSpecificationToSave = productSpecificationConverter.from(createProductSpecification);
productSpecificationRepository.save(productSpecificationToSave);
    }

    @Override
    public List<ProductSpecification> getProductSpecifications() {
        return productSpecificationRepository.findAll();
    }
}
