package com.lelakowski.ERPMetalbud.om.builder;

import com.lelakowski.ERPMetalbud.om.domain.model.OrderStatus;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.lelakowski.ERPMetalbud.common.utils.DateTimeHelper.currentDate;

@Component
@RequiredArgsConstructor
public class ProductOrderBuilder {

    public ProductOrder from(String orderDate, Customer customer) {
        return ProductOrder.builder()
                .orderDate(orderDate)
                .customer(customer)
                .status(OrderStatus.ACTIVE)
                .build();
    }

    public ProductOrder emptyOrder() {
        return ProductOrder.builder()
                .orderDate(currentDate())
                .status(OrderStatus.ACTIVE)
                .build();
    }


}
