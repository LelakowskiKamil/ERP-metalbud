package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandBuilder {

    public Brand from(String externalName) {
        return Brand.builder()
                .externalName(externalName)
                .build();
    }

}
