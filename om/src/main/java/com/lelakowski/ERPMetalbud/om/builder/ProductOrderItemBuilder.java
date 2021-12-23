package com.lelakowski.ERPMetalbud.om.builder;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductOrderItemBuilder {

    public ProductOrderItem from(Integer quantity, ProductOrder productOrder, Long productId) {
        return ProductOrderItem.builder()
                .quantity(quantity)
                .productId(productId)
                .productOrder(productOrder)
                .build();
    }

}
