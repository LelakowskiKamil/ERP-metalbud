package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundAccountWithExternalNameException extends RuntimeException {
    public NotFoundAccountWithExternalNameException(String externalName) {
        super("Account with externalName: "+externalName+" does not exist in database");
    }

}
