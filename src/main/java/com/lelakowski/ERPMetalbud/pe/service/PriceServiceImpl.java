package com.lelakowski.ERPMetalbud.pe.service;

import com.lelakowski.ERPMetalbud.pe.converter.PriceConverter;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

private final PriceConverter priceConverter;
private final PriceRepository priceRepository;


     @Override
     @Transactional
     public void savePrice(double priceValue, String priceCurrency) {
Price priceToSave = priceConverter.from(priceValue,priceCurrency);
priceRepository.save(priceToSave);
     }

     @Override
     public List<Price> getPrices() {
          return priceRepository.findAll();
     }
}
