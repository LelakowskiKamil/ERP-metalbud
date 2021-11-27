package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class BrandBuilder {

    public Brand from(String caption) {
//TODO validation
        return Brand.builder()
                .caption(caption)
                .products(new ArrayList<>())
                .build();
    }

}
