package com.lelakowski.ERPMetalbud.om.validation;

import com.lelakowski.ERPMetalbud.common.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundProductWithIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderItemValidator {

    private final ProductRepository productRepository;

    public void validate(CreateOrderItemCommand createOrderItemCommand) {
        if (createOrderItemCommand == null)
            throw new IllegalCommandContentException(CreateOrderItemCommand.class.getSimpleName());
        validateProductExisting(createOrderItemCommand.getProductId());
    }

    private void validateProductExisting(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundProductWithIdException(productId);
        }
    }

}
