package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.conventer.OrderConverter;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderRepository;
import com.lelakowski.ERPMetalbud.om.validator.CreateOrderValidator;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final CreateOrderValidator createOrderValidator;

    @Override
    public ProductOrder saveOrder(CreateOrderCommand createOrderCommand) {
        createOrderValidator.validate(createOrderCommand);

        ProductOrder productOrderToSave = orderConverter.from(createOrderCommand);
        return orderRepository.save(productOrderToSave);
    }

    @Override
    public List<ProductOrder> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<ProductOrder> getAllCustomerOrders(Long customerId) {
        return orderRepository.getOrdersForCustomer(customerId);
    }

    @Override
    public List<ProductOrderItem> getOrderItems(Long orderId) {
        return orderRepository.getOne(orderId).getProductOrderItems();
    }
}
