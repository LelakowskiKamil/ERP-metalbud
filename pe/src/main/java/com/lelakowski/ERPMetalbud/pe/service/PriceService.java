package com.lelakowski.ERPMetalbud.pe.service;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.web.command.CreatePriceCommand;

import java.util.List;

public interface PriceService {

    Long savePrice(String externalName, double priceValue, String priceCurrency);
    Long savePriceFromCommand(CreatePriceCommand createPriceCommand);

    List<Price> getPrices();

    Price getPrice(Long id);

    Long getPriceIdByExternalName(String externalName);
}
