package com.lelakowski.ERPMetalbud.pe.builder;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceBuilder {

    public Price from(double value, String currency) {
        return Price.builder()
                .value(value)
                .currency(currency)
                .build();
    }

}
