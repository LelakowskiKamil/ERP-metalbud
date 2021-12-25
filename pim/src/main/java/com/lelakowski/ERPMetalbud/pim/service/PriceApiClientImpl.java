package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.web.command.CreatePimPriceCommand;
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
    public Long getPriceIdByExternalName(String externalName) {
        return rt.getForObject("http://localhost:8084/prices/externalName/" + externalName, Long.class);
    }

    @Override
    public ResponseEntity<ResponseEntity> createPrice(CreatePimPriceCommand command) {
        return rt.postForEntity("http://localhost:8084/prices", command, ResponseEntity.class);
    }

}
