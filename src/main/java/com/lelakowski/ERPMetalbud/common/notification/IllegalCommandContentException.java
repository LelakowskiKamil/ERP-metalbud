package com.lelakowski.ERPMetalbud.common.notification;

import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;

public class IllegalCommandContentException extends IllegalArgumentException {
    public IllegalCommandContentException(String message) {
        super("Command "+ message+" cannot be null");
    }
}
