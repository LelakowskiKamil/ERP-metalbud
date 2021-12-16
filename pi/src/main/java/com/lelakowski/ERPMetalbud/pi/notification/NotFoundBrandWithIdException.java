package com.lelakowski.ERPMetalbud.pi.notification;

public class NotFoundBrandWithIdException extends RuntimeException {
    public NotFoundBrandWithIdException(Long brandId) {
        super("Brand with id: "+brandId+" does not exist in database");
    }

}
