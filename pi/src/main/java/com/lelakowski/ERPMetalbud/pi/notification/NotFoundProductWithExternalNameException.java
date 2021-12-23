package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundProductWithExternalNameException extends RuntimeException {
    public NotFoundProductWithExternalNameException(String externalName) {
        super("Product with externalName: "+externalName+" does not exist in database");
    }

}
