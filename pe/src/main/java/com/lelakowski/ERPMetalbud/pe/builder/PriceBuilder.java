package com.lelakowski.ERPMetalbud.pe.builder;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.web.command.CreatePriceCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceBuilder {

    public Price from(String externalName,double value, String currency) {
        return Price.builder()
                .externalName(externalName)
                .value(value)
                .currency(currency)
                .build();
    }
    public Price fromCommand(CreatePriceCommand command) {
        return Price.builder()
                .externalName(command.getExternalName())
                .value(command.getValue())
                .currency(command.getCurrency())
                .build();
    }
}
