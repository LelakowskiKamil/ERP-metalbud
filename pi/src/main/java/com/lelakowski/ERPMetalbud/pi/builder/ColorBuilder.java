package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class ColorBuilder {

    public Color from(String oem, String externalName) {
        return Color.builder()
                .oem(oem)
                .externalName(externalName)
                .build();
    }

}
