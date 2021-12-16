package com.lelakowski.ERPMetalbud.pi.notification;

public class NotFoundVendorWithIdException extends RuntimeException {
    public NotFoundVendorWithIdException(Long vendorId) {
        super("Vendor with id: "+vendorId+" does not exist in database");
    }

}
