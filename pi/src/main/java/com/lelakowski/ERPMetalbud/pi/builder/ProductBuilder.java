package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
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
            String caption,
            ProductDetails productDetails,
            Price price,
            Vendor vendor,
            Brand brand
    ) {
        return Product.builder()
                .productDetails(productDetails)
                .caption(caption)
                .brand(brand)
                .vendor(vendor)
                .price(price)
                .build();
    }

}
