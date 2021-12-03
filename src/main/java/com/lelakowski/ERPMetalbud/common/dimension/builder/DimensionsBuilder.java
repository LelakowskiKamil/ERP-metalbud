package com.lelakowski.ERPMetalbud.common.dimension.builder;

import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DimensionsBuilder {

    public Dimensions from(String caption, Dimension height, Dimension length, Dimension width) {
        return Dimensions.builder()
                .caption(caption)
                .height(height)
                .length(length)
                .width(width)
                .productSpecifications(new ArrayList<>())
                .build();
    }

}
