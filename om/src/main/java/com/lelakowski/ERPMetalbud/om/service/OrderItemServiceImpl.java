package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.builder.ProductOrderItemBuilder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderItemRepository;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderRepository;
import com.lelakowski.ERPMetalbud.om.validation.CreateOrderItemValidator;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductOrderItemBuilder productOrderItemBuilder;
    private final CreateOrderItemValidator createOrderItemValidator;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Long saveOrderItem(CreateOrderItemCommand createOrderItemCommand) {
        createOrderItemValidator.validate(createOrderItemCommand);

        Product product = productRepository.getOne(createOrderItemCommand.getProductId());
        ProductOrder productOrder = orderRepository.getOne(createOrderItemCommand.getOrderId());

        ProductOrderItem productOrderItemToSave = productOrderItemBuilder.from(
                createOrderItemCommand.getQuantity(),
                productOrder,
                product
        );

        ProductOrderItem orderItem = orderItemRepository.save(productOrderItemToSave);

        return orderItem.getId();
    }

}
