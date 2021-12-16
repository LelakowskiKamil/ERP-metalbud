package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.common.color.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.pi.builder.ProductDetailsBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductDetailsRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.validation.CreateProductDetailsValidator;
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
    private final CreateProductDetailsValidator createProductDetailsValidator;

    @Transactional
    @Override
    public Long saveProductDetails(CreateProductDetailsCommand createProductDetailsCommand) {
        createProductDetailsValidator.validate(createProductDetailsCommand);
        ProductSpecification productSpecification =
                productSpecificationRepository.getOne(createProductDetailsCommand.getProductSpecificationId());

        Color color = colorRepository.getColorByOEM(createProductDetailsCommand.getOem()).get();

        ProductDetails productDetailsToSave = productDetailsBuilder.from(
                productSpecification,
                color
        );

        ProductDetails productDetails = productDetailsRepository.save(productDetailsToSave);
        return productDetails.getId();
    }

    @Override
    public List<ProductDetails> getProductDetails() {
        return productDetailsRepository.findAll();
    }

}

