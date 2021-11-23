package com.lelakowski.ERPMetalbud.om.validator;

import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderItemValidator {

    private final ProductRepository productRepository;

    public void validate(CreateOrderItemCommand createOrderItemCommand) {
        if (createOrderItemCommand == null) throw new IllegalArgumentException();
        validateProductExisting(createOrderItemCommand.getProductId());
    }

    private void validateProductExisting(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Nie ma takiego produktu");
        }
    }

}
