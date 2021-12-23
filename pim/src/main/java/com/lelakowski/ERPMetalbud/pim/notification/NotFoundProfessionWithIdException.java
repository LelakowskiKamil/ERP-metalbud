package com.lelakowski.ERPMetalbud.pim.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundProfessionWithIdException extends RuntimeException {
    public NotFoundProfessionWithIdException(Long professionId) {
        super("Profession with id: " + professionId + " does not exist in database");
    }

}
