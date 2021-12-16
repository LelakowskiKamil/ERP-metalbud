package com.lelakowski.ERPMetalbud.pe.service;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;

import java.util.List;

public interface PriceService {

    Long savePrice(double priceValue, String priceCurrency);

    List<Price> getPrices();
}
