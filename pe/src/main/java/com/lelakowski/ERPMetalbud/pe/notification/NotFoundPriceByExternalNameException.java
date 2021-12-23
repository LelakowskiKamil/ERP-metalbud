package com.lelakowski.ERPMetalbud.pe.notification;

public class NotFoundPriceByExternalNameException extends RuntimeException {
    public NotFoundPriceByExternalNameException(String externalName) {
        super("Not found price with externalName: "+externalName);
    }

}
