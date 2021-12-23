package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundProductSpecificationWithIdException extends RuntimeException {
    public NotFoundProductSpecificationWithIdException(Long productSpecificationId) {
        super("ProductSpecification with id: "+productSpecificationId+" does not exist in database");
    }

}
