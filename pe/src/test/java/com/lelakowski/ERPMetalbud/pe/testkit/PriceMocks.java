package com.lelakowski.ERPMetalbud.pe.testkit;

import com.lelakowski.ERPMetalbud.pe.domain.model.Price;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceMocks {

    public static Price price1 = mockPrice(1L, "PLN", 33.3);
    public static Price price2 = mockPrice(2L, "EUR", 36.3);
    public static Price price3 = mockPrice(3L, "PLN", 311.3);
    public static Price price4 = mockPrice(4L, "EUR", 13.3);

    public static Price mockPrice(Long id, String currency, Double value) {
        Price price = mock(Price.class);
        when(price.getId()).thenReturn(id);
        when(price.getCurrency()).thenReturn(currency);
        when(price.getValue()).thenReturn(value);
        return price;
    }
}
