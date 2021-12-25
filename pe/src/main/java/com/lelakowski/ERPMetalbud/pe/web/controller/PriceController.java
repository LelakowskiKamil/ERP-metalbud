package com.lelakowski.ERPMetalbud.pe.web.controller;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.service.PriceService;
import com.lelakowski.ERPMetalbud.pe.web.command.CreatePriceCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PriceController {

    private final PriceService priceService;


    @GetMapping("/prices")
    public ResponseEntity<List> getPrices() {
        return new ResponseEntity(priceService.getPrices(), HttpStatus.OK);
    }

    @GetMapping(value = "/prices/externalName/{externalName}")
    public ResponseEntity<Long> getPriceIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity<>(priceService.getPriceIdByExternalName(externalName), HttpStatus.OK);
    }

    @GetMapping(value = "/prices/{id}")
    public ResponseEntity<Price> getPriceIdByExternalName(@PathVariable("id") Long id) {
        return new ResponseEntity<>(priceService.getPrice(id), HttpStatus.OK);
    }


    @PostMapping(path = "/prices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Price> createPrice(@RequestBody @Valid CreatePriceCommand createPriceCommand) {
        priceService.savePriceFromCommand(createPriceCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
