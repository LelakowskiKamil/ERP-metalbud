package com.lelakowski.ERPMetalbud.pi.conventer;

import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.domain.dimension.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.common.domain.dimension.service.DimensionsService;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSpecificationConverter {

    private final DimensionsService dimensionsService;

    public ProductSpecification from(CreateProductSpecification createProductSpecification) {
        if (createProductSpecification == null) throw new IllegalArgumentException();
Dimensions dimensions = dimensionsService.saveDimensions(createProductSpecification.getDimensions());

        return ProductSpecification.builder()
                .caption(createProductSpecification.getCaption())
                .dimensions(dimensions)
                .build();
    }

}
