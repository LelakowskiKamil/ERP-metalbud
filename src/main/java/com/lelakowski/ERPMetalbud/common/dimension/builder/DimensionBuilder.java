package com.lelakowski.ERPMetalbud.common.dimension.builder;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DimensionBuilder {

    public Dimension from(Double value, String unit) {
        if (value == null || unit.equals("")) throw new IllegalArgumentException();

        return Dimension.builder()
                .value(value)
                .unit(unit)
                .dimensionsHeight(new ArrayList<>())
                .dimensionsLength(new ArrayList<>())
                .dimensionsWidth(new ArrayList<>())
                .build();
    }

}
