package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundColorWithExternalNameException extends RuntimeException {
    public NotFoundColorWithExternalNameException(String externalName) {
        super("Color with externalName: " + externalName + " does not exist in database");
    }

}
