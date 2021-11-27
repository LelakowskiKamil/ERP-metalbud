package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ProductDetailsBuilder {

    public ProductDetails from(ProductSpecification productSpecification, Color color) {
        //TODO validation
        return ProductDetails.builder()
                .productSpecification(productSpecification)
                .color(color)
                .products(new ArrayList<>())
                .build();
    }

}
