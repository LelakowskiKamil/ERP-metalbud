package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;

import java.util.List;

public interface OrderService {

    ProductOrder saveOrder(CreateOrderCommand createOrderCommand);

    List<ProductOrder> getOrders();

    List<ProductOrder> getAllCustomerOrders(Long customerId);

    List<ProductOrderItem> getOrderItems(Long orderId);


}
