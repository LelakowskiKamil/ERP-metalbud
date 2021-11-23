package com.lelakowski.ERPMetalbud.pe.service;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;

import java.util.List;

public interface PriceService {

     void savePrice(double priceValue, String priceCurrency);

     List<Price> getPrices();
}
