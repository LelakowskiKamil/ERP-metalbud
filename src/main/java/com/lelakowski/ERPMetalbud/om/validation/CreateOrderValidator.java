package com.lelakowski.ERPMetalbud.om.validation;

import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderValidator {

    private final CustomerRepository customerRepository;
    private final CreateOrderItemValidator createOrderItemValidator;

    public void validate(CreateOrderCommand createOrderCommand) {
        if (createOrderCommand == null) throw new IllegalArgumentException(); //TODO notification

        validateCustomerExisting(createOrderCommand.getCustomerId());
        createOrderCommand.getOrderItems()
                .forEach(createOrderItemValidator::validate);
    }

    private void validateCustomerExisting(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Nie ma takiego produktu"); //TODO notification
        }
    }
}
