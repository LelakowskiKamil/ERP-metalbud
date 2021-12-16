package com.lelakowski.ERPMetalbud.pe.service;

import com.lelakowski.ERPMetalbud.pe.builder.PriceBuilder;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceBuilder priceBuilder;
    private final PriceRepository priceRepository;


    @Override
    public Long savePrice(double priceValue, String priceCurrency) {
        Price priceToSave = priceBuilder.from(priceValue, priceCurrency);
        priceRepository.save(priceToSave);
        return priceToSave.getId();
    }

    @Override
    public List<Price> getPrices() {
        return priceRepository.findAll();
    }
}
