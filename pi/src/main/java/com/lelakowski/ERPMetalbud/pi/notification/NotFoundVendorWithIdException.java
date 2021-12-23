package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundVendorWithIdException extends RuntimeException {
    public NotFoundVendorWithIdException(Long vendorId) {
        super("Vendor with id: "+vendorId+" does not exist in database");
    }

}
