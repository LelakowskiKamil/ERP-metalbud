package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundEmployeeWithExternalNameException extends RuntimeException {
    public NotFoundEmployeeWithExternalNameException(String externalName) {
        super("Employee with externalName: "+externalName+" does not exist in database");
    }

}
