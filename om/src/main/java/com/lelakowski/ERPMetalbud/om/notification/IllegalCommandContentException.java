package com.lelakowski.ERPMetalbud.om.notification;

public class IllegalCommandContentException extends IllegalArgumentException {
    public IllegalCommandContentException(String message) {
        super("Command " + message + " cannot be null");
    }
}
