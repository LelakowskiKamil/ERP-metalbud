package com.lelakowski.ERPMetalbud.om.builder;

import com.lelakowski.ERPMetalbud.om.domain.model.OrderStatus;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.lelakowski.ERPMetalbud.common.utils.DateTimeHelper.currentDate;

@Component
@RequiredArgsConstructor
public class ProductOrderBuilder {

    public ProductOrder from(String orderDate, Long customerId) {
        return ProductOrder.builder()
                .orderDate(orderDate)
                .customerId(customerId)
                .status(OrderStatus.ACTIVE)
                .build();
    }

    public ProductOrder emptyOrder(Long customerId) {
        return ProductOrder.builder()
                .customerId(customerId)
                .orderDate(currentDate())
                .status(OrderStatus.ACTIVE)
                .build();
    }


}
