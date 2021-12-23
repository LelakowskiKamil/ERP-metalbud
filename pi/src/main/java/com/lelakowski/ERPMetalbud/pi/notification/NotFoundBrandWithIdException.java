package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundBrandWithIdException extends RuntimeException {
    public NotFoundBrandWithIdException(Long brandId) {
        super("Brand with id: "+brandId+" does not exist in database");
    }

}
