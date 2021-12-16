package com.lelakowski.ERPMetalbud.pi.notification;

public class NotFoundProductWithIdException extends RuntimeException {
    public NotFoundProductWithIdException(Long productId) {
        super("Product with id: "+productId+" does not exist in database");
    }

}
