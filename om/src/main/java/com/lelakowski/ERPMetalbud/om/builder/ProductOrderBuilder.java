package com.lelakowski.ERPMetalbud.om.builder;

import com.lelakowski.ERPMetalbud.om.domain.model.OrderStatus;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


@Component
@RequiredArgsConstructor
public class ProductOrderBuilder {

    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String fromGregorianCalendar(GregorianCalendar date) {
        return sdfDate.format(date.getTime());
    }

    private String currentDate() {
        Date now = new Date();
        return sdfDate.format(now);
    }

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
