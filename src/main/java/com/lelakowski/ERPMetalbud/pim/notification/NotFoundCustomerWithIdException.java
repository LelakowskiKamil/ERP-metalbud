package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundCustomerWithIdException extends RuntimeException {
    public NotFoundCustomerWithIdException(Long customerId) {
        super("Customer with id: "+customerId+" does not exist in database");
    }

}
