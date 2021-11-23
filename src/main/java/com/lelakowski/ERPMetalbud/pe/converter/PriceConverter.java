package com.lelakowski.ERPMetalbud.pe.converter;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceConverter {

    public Price from(double value, String currency) {
        if (currency == null || currency.equals("")) throw new IllegalArgumentException();

        return Price.builder()
                .value(value)
                .currency(currency)
                .build();
    }

}
