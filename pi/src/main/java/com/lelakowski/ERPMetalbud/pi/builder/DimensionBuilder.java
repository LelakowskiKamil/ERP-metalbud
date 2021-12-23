package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Dimension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DimensionBuilder {

    public Dimension from(Double value, String unit) {
        return Dimension.builder()
                .value(value)
                .unit(unit)
                .build();
    }

}
