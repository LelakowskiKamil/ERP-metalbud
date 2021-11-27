package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.common.color.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.pi.builder.ProductDetailsBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.*;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductDetailsRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductDetailsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductDetailsBuilder productDetailsBuilder;
    private final ColorRepository colorRepository;
    private final ProductSpecificationRepository productSpecificationRepository;

    @Transactional
    @Override
    public Long saveProductDetails(CreateProductDetailsCommand createProductDetailsCommand) {
        //TODO validation
        ProductSpecification productSpecification =
                productSpecificationRepository.getOne(createProductDetailsCommand.getProductSpecificationId());

        Color color = colorRepository.getColorByOEM(createProductDetailsCommand.getOem());

        ProductDetails productDetailsToSave = productDetailsBuilder.from(
                productSpecification,
                color
        );

        ProductDetails productDetails = productDetailsRepository.save(productDetailsToSave);
        saveReferences(productDetails, productSpecification, color);

        return productDetails.getId();
    }

    @Override
    public List<ProductDetails> getProductDetails() {
        return productDetailsRepository.findAll();
    }

    private void saveReferences(
            ProductDetails productDetailsReference,
            ProductSpecification productSpecification,
            Color color
    ){
        productSpecification.addToProductDetailsList(productDetailsReference);
        color.addToProductDetailsList(productDetailsReference);
    }

}


