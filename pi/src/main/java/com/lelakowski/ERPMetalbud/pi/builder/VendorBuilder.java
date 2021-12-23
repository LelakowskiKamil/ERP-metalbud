package com.lelakowski.ERPMetalbud.pi.builder;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendorBuilder {

    public Vendor from(String externalName) {
        return Vendor.builder()
                .externalName(externalName)
                .build();
    }

}
