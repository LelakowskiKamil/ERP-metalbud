package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundCustomerWithExternalNameException extends RuntimeException {
    public NotFoundCustomerWithExternalNameException(String externalName) {
        super("Customer with externalName: "+externalName+" does not exist in database");
    }

}
