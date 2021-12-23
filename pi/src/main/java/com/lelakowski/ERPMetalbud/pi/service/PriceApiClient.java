package com.lelakowski.ERPMetalbud.pi.service;

import org.springframework.http.ResponseEntity;

public interface PriceApiClient {

     ResponseEntity<ResponseEntity> createPrice(String externalName, double priceValue, String priceCurrency);

     Long getPriceIdByExternalName(String externalName);
}
