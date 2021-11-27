package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class VendorBuilder {

    public Vendor from(String caption) {
//TODO validation
        return Vendor.builder()
                .caption(caption)
                .products(new ArrayList<>())
                .build();
    }

}
