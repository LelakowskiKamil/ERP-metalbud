package com.lelakowski.ERPMetalbud.om.validator;

import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class CreateOrderValidator {

    private final CustomerRepository customerRepository;
    private final CreateOrderItemValidator createOrderItemValidator;

    public void validate(CreateOrderCommand createOrderCommand) {
        if (createOrderCommand == null) throw new IllegalArgumentException();

        validateCustomerExisting(createOrderCommand.getCustomerId());
        createOrderCommand.getOrderItems()
                .forEach(createOrderItemValidator::validate);
    }

    private void validateCustomerExisting(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Nie ma takiego produktu");
        }
    }
}
