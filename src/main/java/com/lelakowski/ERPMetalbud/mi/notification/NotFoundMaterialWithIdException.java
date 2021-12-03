package com.lelakowski.ERPMetalbud.mi.notification;

public class NotFoundMaterialWithIdException extends RuntimeException {
    public NotFoundMaterialWithIdException(Long materialId) {
        super("Material with id: "+materialId+" does not exist in database");
    }

}
