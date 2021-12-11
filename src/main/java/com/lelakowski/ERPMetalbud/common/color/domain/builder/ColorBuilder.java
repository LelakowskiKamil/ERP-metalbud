package com.lelakowski.ERPMetalbud.common.color.domain.builder;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ColorBuilder {

    public Color from(String oem, String caption) {
        return Color.builder()
                .oem(oem)
                .caption(caption)
                .productDetails(new ArrayList<>())
                .build();
    }

}
