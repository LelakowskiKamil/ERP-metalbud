package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundPrivilegesWithIdException extends RuntimeException {
    public NotFoundPrivilegesWithIdException(Long privilegesId) {
        super("Privileges with id: "+privilegesId+" does not exist in database");
    }

}
