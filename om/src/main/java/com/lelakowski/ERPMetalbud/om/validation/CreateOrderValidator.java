package com.lelakowski.ERPMetalbud.om.validation;

import com.lelakowski.ERPMetalbud.common.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderValidator {

    private final CreateOrderItemValidator createOrderItemValidator;

    public void validate(CreateOrderCommand createOrderCommand) {
        if (createOrderCommand == null)
            throw new IllegalCommandContentException(CreateOrderItemCommand.class.getSimpleName());
        createOrderCommand.getOrderItems()
                .forEach(createOrderItemValidator::validate);
    }

}
