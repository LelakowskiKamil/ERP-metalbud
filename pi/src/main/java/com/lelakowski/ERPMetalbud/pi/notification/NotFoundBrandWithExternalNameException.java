package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundBrandWithExternalNameException extends RuntimeException {
    public NotFoundBrandWithExternalNameException(String externalName) {
        super("Brand with externalName: "+externalName+" does not exist in database");
    }

}
