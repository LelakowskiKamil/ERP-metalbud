package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.pi.domain.model.Dimensions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DimensionsBuilder {

    public Dimensions from(String caption, Dimension height, Dimension length, Dimension width) {
        return Dimensions.builder()
                .caption(caption)
                .height(height)
                .width(width)
                .length(length)
                .build();
    }

}
