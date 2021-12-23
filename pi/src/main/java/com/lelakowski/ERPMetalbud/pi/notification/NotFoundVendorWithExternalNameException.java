package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundVendorWithExternalNameException extends RuntimeException {
    public NotFoundVendorWithExternalNameException(String externalName) {
        super("Vendor with externalName: "+externalName+" does not exist in database");
    }

}
