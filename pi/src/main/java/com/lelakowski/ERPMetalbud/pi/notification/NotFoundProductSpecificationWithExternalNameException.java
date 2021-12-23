package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundProductSpecificationWithExternalNameException extends RuntimeException {
    public NotFoundProductSpecificationWithExternalNameException(String externalName) {
        super("ProductSpecification with externalName: "+externalName+" does not exist in database");
    }

}
