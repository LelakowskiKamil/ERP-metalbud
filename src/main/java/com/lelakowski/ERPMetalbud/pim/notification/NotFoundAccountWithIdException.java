package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundAccountWithIdException extends RuntimeException {
    public NotFoundAccountWithIdException(Long accountId) {
        super("Account with id: "+accountId+" does not exist in database");
    }

}
