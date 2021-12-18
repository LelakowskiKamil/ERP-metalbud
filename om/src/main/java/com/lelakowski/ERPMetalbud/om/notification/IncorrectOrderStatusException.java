package com.lelakowski.ERPMetalbud.om.notification;

import com.lelakowski.ERPMetalbud.om.domain.model.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectOrderStatusException extends RuntimeException {
    public IncorrectOrderStatusException(OrderStatus actualState, OrderStatus expectedState) {
        super("Incorrect order status before update. Status should be in state "
                + expectedState + ". Actual state: " + actualState + ".");
    }

}
