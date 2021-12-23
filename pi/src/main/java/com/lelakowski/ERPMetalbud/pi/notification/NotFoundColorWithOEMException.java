package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundColorWithOEMException extends RuntimeException {
    public NotFoundColorWithOEMException(String oem) {
        super("Color with oem: " + oem + " does not exist in database");
    }

}
