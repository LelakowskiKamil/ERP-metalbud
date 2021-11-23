package com.lelakowski.ERPMetalbud.om.service;

import com.lelakowski.ERPMetalbud.om.conventer.OrderItemConverter;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.domain.repository.repository.OrderItemRepository;
import com.lelakowski.ERPMetalbud.om.validator.CreateOrderItemValidator;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemConverter orderItemConverter;
    private final CreateOrderItemValidator createOrderItemValidator;

    @Override
    public ProductOrderItem saveOrderItem(CreateOrderItemCommand createOrderItemCommand) {
        createOrderItemValidator.validate(createOrderItemCommand);

        ProductOrderItem productOrderItemToSave = orderItemConverter.from(createOrderItemCommand);
        return orderItemRepository.save(productOrderItemToSave);
    }

}
