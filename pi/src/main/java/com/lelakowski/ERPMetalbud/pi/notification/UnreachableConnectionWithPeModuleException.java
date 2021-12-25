package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class UnreachableConnectionWithPeModuleException extends RuntimeException {
    public UnreachableConnectionWithPeModuleException() {
        super("Unable to establish connection with the Pe module.");
    }

}
