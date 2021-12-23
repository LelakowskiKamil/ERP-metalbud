package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductBuilder {

    public Product from(
            String externalName,
            ProductDetails productDetails,
            Long priceId,
            Vendor vendor,
            Brand brand
    ) {
        return Product.builder()
                .productDetails(productDetails)
                .externalName(externalName)
                .brand(brand)
                .vendor(vendor)
                .priceId(priceId)
                .build();
    }

}
