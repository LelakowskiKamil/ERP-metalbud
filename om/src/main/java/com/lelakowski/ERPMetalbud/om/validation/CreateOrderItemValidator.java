package com.lelakowski.ERPMetalbud.om.validation;

import com.lelakowski.ERPMetalbud.om.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderItemValidator {

    public void validate(CreateOrderItemCommand createOrderItemCommand) {
        if (createOrderItemCommand == null)
            throw new IllegalCommandContentException(CreateOrderItemCommand.class.getSimpleName());
    }


}
