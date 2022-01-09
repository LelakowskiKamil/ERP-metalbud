package com.lelakowski.ERPMetalbud.pim.notification;

public class IllegalCommandContentException extends IllegalArgumentException {
    public IllegalCommandContentException(String message) {
        super("Command " + message + " cannot be null");
    }
}
