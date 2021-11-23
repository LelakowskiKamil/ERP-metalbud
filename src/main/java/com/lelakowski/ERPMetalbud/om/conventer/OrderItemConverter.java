package com.lelakowski.ERPMetalbud.om.conventer;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {

    private final ProductRepository productRepository;

    public ProductOrderItem from(CreateOrderItemCommand createOrderItemCommand) {
        if (createOrderItemCommand == null) throw new IllegalArgumentException();

        //TODO validation does the product with the given id exist
        Product product = productRepository.getOne(createOrderItemCommand.getProductId());

        return ProductOrderItem.builder()
                .quantity(createOrderItemCommand.getQuantity())
                .product(product)
                .build();
    }

}
