package com.lelakowski.ERPMetalbud.pi.conventer;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandConverter {

    public Brand from(CreateBrandCommand createBrandCommand) {
        if (createBrandCommand == null) throw new IllegalArgumentException();

        return Brand.builder()
                .caption(createBrandCommand.getCaption())
                .build();
    }

}
