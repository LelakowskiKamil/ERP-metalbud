package com.lelakowski.ERPMetalbud.om.validation;

import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderItemValidator {

    private final ProductRepository productRepository;

    public void validate(CreateOrderItemCommand createOrderItemCommand) {
        if (createOrderItemCommand == null) throw new IllegalArgumentException(); //TODO notification
        validateProductExisting(createOrderItemCommand.getProductId());
    }

    private void validateProductExisting(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Nie ma takiego produktu"); //TODO notification
        }
    }

}
