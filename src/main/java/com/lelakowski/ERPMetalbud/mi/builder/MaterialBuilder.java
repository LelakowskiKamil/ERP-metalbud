package com.lelakowski.ERPMetalbud.mi.builder;

import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MaterialBuilder {

    public Material from(String caption) {
        return Material.builder()
                .caption(caption)
                .billOfMaterialItems(new ArrayList<>())
                .build();
    }

}
