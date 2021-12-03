package com.lelakowski.ERPMetalbud.common.color.notification;

public class NotFoundColorWithOEMException extends RuntimeException {
    public NotFoundColorWithOEMException(String oem) {
        super("Color with oem: "+oem+" does not exist in database");
    }

}
