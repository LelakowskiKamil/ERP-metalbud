package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.web.command.CreatePiPriceCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceApiClientImpl implements PriceApiClient {

    private final RestTemplate rt;

    @Override
    public ResponseEntity<ResponseEntity> createPrice(String externalName, double priceValue, String priceCurrency) {
        CreatePiPriceCommand command = new CreatePiPriceCommand(externalName, priceValue, priceCurrency);
        return rt.postForEntity("http://localhost:8084/prices", command, ResponseEntity.class);
    }

    @Override
    public Long getPriceIdByExternalName(String externalName) {
        return rt.getForObject("http://localhost:8084/prices/externalname/" + externalName, Long.class);
    }

}
