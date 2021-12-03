package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundAddressWithIdException extends RuntimeException {
    public NotFoundAddressWithIdException(Long addressId) {
        super("Address with id: "+addressId+" does not exist in database");
    }

}
