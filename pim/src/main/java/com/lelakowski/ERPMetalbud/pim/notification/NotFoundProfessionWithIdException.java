package com.lelakowski.ERPMetalbud.pim.notification;

public class NotFoundProfessionWithIdException extends RuntimeException {
    public NotFoundProfessionWithIdException(Long professionId) {
        super("Profession with id: " + professionId + " does not exist in database");
    }

}
