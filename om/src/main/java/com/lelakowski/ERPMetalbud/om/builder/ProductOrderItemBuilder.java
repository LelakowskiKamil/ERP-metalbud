package com.lelakowski.ERPMetalbud.om.builder;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductOrderItemBuilder {

    public ProductOrderItem from(Integer quantity, ProductOrder productOrder, Product product) {
        return ProductOrderItem.builder()
                .quantity(quantity)
                .product(product)
                .productOrder(productOrder)
                .build();
    }

}
