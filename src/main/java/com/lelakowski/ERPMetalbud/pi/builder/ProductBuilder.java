package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductBuilder {

    public Product from(
            String caption,
            ProductDetails productDetails,
            Price price,
            List<BillOfMaterialItem> bom,
            Vendor vendor,
            Brand brand
    ) {
//TODO validation
        return Product.builder()
                .productDetails(productDetails)
                .caption(caption)
                .brand(brand)
                .vendor(vendor)
                .price(price)
                .billOfMaterialItems(bom)
                .productOrderItems(new ArrayList<>())
                .build();
    }

}
