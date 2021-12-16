package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSpecificationBuilder {

    public ProductSpecification from(String caption, Dimensions dimensions) {
        return ProductSpecification.builder()
                .caption(caption)
                .dimensions(dimensions)
                .build();
    }

}
