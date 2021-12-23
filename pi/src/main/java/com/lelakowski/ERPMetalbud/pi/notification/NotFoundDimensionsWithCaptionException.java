package com.lelakowski.ERPMetalbud.pi.notification;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundDimensionsWithCaptionException extends RuntimeException {
    public NotFoundDimensionsWithCaptionException(String caption) {
        super("Vendor with caption: "+caption+" does not exist in database");
    }

}
