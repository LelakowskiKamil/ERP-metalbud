package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.web.command.CreatePiPriceCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceApiClientImpl implements PriceApiClient {

    private final RestTemplate rt;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackCreatePrice", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public ResponseEntity<ResponseEntity> createPrice(@RequestBody @Valid CreatePiPriceCommand createPriceCommand) {
        return rt.postForEntity("http://localhost:8084/prices", createPriceCommand, ResponseEntity.class);
    }

    @Override
    public Long getPriceIdByExternalName(String externalName) {
        return rt.getForObject("http://localhost:8084/prices/externalName/" + externalName, Long.class);
    }

    @Override
    public ResponseEntity<ResponseEntity> fallbackCreatePrice(CreatePiPriceCommand createPriceCommand) {
        createPriceCommandList.add(createPriceCommand);
        log.info("PE module doesn't respond to the POST request: " + createPriceCommand);
        log.info("List of failed request:  " + createPriceCommandList.toString());
        throw new UnsupportedOperationException();
    }


}
