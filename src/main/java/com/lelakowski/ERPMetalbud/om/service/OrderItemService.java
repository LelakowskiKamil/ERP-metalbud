package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;

public interface OrderItemService {

    Long saveOrderItem(CreateOrderItemCommand createOrderItemCommand);

}
