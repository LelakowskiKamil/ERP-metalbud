package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.web.command.CreatePimPriceCommand;
import org.springframework.http.ResponseEntity;

public interface PriceApiClient {

     Long getPriceIdByExternalName(String externalName);

    ResponseEntity<ResponseEntity> createPrice(CreatePimPriceCommand command);

}
