package com.lelakowski.ERPMetalbud.om.builder;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductOrderBuilder {

    public ProductOrder from(Date orderDate, Customer customer, List<ProductOrderItem> productOrderItems) {
//TODO validation
        return ProductOrder.builder()
                .orderDate(orderDate)
                .customer(customer)
                .productOrderItems(productOrderItems)
                .productOrderItems(new ArrayList<>())
                .build();
    }


}
