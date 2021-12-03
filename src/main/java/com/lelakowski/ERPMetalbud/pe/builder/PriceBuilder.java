package com.lelakowski.ERPMetalbud.pe.builder;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PriceBuilder {

    public Price from(double value, String currency) {
        return Price.builder()
                .value(value)
                .currency(currency)
                .employees(new ArrayList<>())
                .products(new ArrayList<>())
                .build();
    }

}
