package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ProductSpecificationBuilder {

    public ProductSpecification from(String caption, Dimensions dimensions) {
//TODO validation
        return ProductSpecification.builder()
                .caption(caption)
                .dimensions(dimensions)
                .productDetails(new ArrayList<>())
                .build();
    }

}
