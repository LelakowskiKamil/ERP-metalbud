package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundProductWithIdException extends RuntimeException {
    public NotFoundProductWithIdException(Long productId) {
        super("Product with id: "+productId+" does not exist in database");
    }

}
