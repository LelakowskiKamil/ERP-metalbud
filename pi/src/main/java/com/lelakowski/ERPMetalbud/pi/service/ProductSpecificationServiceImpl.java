package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.ProductSpecificationBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundProductSpecificationWithExternalNameException;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    private final ProductSpecificationRepository productSpecificationRepository;
    private final ProductSpecificationBuilder productSpecificationBuilder;
    private final DimensionsService dimensionsService;
    private final DimensionsRepository dimensionsRepository;

    @Transactional
    @Override
    public Long saveProductSpecification(CreateProductSpecification createProductSpecification) {
        Long dimensionsId = dimensionsService.saveDimensions(createProductSpecification.getDimensions());
        Dimensions dimensions = dimensionsRepository.getOne(dimensionsId);

        ProductSpecification productSpecificationToSave = productSpecificationBuilder.from(
                createProductSpecification.getCaption(),
                dimensions
        );

        ProductSpecification productSpecification = productSpecificationRepository.save(productSpecificationToSave);
        return productSpecification.getId();
    }

    @Override
    public List<ProductSpecification> getProductSpecifications() {
        return productSpecificationRepository.findAll();
    }

    @Override
    public Long getProductSpecificationIdByExternalName(String externalName) {
        Optional<Long> productSpecificationIdOpt =
                productSpecificationRepository.findProductSpecificationIdByExternalName(externalName);
        if (productSpecificationIdOpt.isEmpty())
            throw new NotFoundProductSpecificationWithExternalNameException(externalName);

        return productSpecificationIdOpt.get();
    }

}
