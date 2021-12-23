package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.ProductDetailsBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Color;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductDetailsRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundColorWithOEMException;
import com.lelakowski.ERPMetalbud.pi.validation.CreateProductDetailsValidator;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductDetailsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        Optional<Long> colorIdOpt = colorRepository.findColorByOEM(createProductDetailsCommand.getOem());
        Long colorId;
        if (colorIdOpt.isPresent()) {
            colorId = colorIdOpt.get();
        } else {
            throw new NotFoundColorWithOEMException(createProductDetailsCommand.getOem());
        }

        Color color = colorRepository.getOne(colorId);

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


