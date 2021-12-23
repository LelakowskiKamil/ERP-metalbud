package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSpecificationBuilder {

    public ProductSpecification from(String externalName, Dimensions dimensions) {
        return ProductSpecification.builder()
                .externalName(externalName)
                .dimensions(dimensions)
                .build();
    }

}
