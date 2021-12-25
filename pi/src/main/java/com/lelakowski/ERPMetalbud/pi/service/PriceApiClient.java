package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.web.command.CreatePiPriceCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public interface PriceApiClient {

     ResponseEntity<ResponseEntity> createPrice(@RequestBody @Valid CreatePiPriceCommand createPriceCommand);

     Long getPriceIdByExternalName(String externalName);

     List<CreatePiPriceCommand> createPriceCommandList = new ArrayList<>();

     ResponseEntity fallbackCreatePrice(CreatePiPriceCommand createPriceCommand);
}
