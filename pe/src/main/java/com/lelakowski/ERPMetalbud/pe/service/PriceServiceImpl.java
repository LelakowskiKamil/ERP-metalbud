package com.lelakowski.ERPMetalbud.pe.service;

import com.lelakowski.ERPMetalbud.pe.builder.PriceBuilder;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pe.notification.NotFoundPriceByExternalNameException;
import com.lelakowski.ERPMetalbud.pe.web.command.CreatePriceCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceBuilder priceBuilder;
    private final PriceRepository priceRepository;


    @Override
    public Long savePrice(String externalName, double priceValue, String priceCurrency) {
        Price priceToSave = priceBuilder.from(externalName,priceValue, priceCurrency);
        priceRepository.save(priceToSave);
        return priceToSave.getId();
    }

    @Override
    public Long savePriceFromCommand(CreatePriceCommand createPriceCommand){
        Price priceToSave = priceBuilder.fromCommand(createPriceCommand);
        priceRepository.save(priceToSave);
        return priceToSave.getId();
    }

    @Override
    public List<Price> getPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getPrice(Long id){
        return priceRepository.getOne(id);
    }

    @Override
    public Long getPriceIdByExternalName(String externalName) {
        Optional<Long> price = priceRepository.getPriceByExternalName(externalName);
        if (price.isEmpty()) {
            throw new NotFoundPriceByExternalNameException(externalName);
        }
        return price.get();
    }
}
