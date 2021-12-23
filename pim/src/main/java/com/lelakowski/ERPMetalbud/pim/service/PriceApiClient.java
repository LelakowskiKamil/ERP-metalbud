package com.lelakowski.ERPMetalbud.pim.service;

import org.springframework.http.ResponseEntity;

public interface PriceApiClient {

     Long getPriceIdByExternalName(String externalName);

     ResponseEntity<ResponseEntity> createPrice(String externalName, double priceValue, String priceCurrency);

}
