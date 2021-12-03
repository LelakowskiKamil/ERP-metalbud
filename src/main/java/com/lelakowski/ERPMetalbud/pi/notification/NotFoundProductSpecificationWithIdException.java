package com.lelakowski.ERPMetalbud.pi.notification;

public class NotFoundProductSpecificationWithIdException extends RuntimeException {
    public NotFoundProductSpecificationWithIdException(Long productSpecificationId) {
        super("ProductSpecification with id: "+productSpecificationId+" does not exist in database");
    }

}
