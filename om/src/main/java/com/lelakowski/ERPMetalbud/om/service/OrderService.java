package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;

import java.util.List;

public interface OrderService {

    Long saveOrder(CreateOrderCommand createOrderCommand);

    List<ProductOrder> getOrders();

    List<ProductOrder> getAllCustomerOrders(Long customerId);

    List<ProductOrderItem> getOrderItems(Long orderId);

    Long addOrderItemToOrder(CreateOrderItemCommand createOrderItemCommand);

    void confirm(Long orderId);

    void sendOffer(Long orderId);

    void closeOffer(Long orderId);

    void revertOffer(Long orderId);
}
