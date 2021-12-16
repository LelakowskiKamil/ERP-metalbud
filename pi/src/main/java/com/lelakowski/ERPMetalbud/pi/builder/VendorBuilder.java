package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendorBuilder {

    public Vendor from(String caption) {
        return Vendor.builder()
                .caption(caption)
                .build();
    }

}
