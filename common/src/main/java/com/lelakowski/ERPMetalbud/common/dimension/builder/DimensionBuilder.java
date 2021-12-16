package com.lelakowski.ERPMetalbud.common.dimension.builder;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
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
