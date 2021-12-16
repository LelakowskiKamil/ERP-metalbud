package com.lelakowski.ERPMetalbud.common.color.builder;

import com.lelakowski.ERPMetalbud.common.color.domain.model.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColorBuilder {

    public Color from(String oem, String caption) {
        return Color.builder()
                .oem(oem)
                .caption(caption)
                .build();
    }

}
