package com.lelakowski.ERPMetalbud.pi.conventer;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendorConverter {

    public Vendor from(CreateVendorCommand createVendorCommand) {
        if (createVendorCommand == null) throw new IllegalArgumentException();

        return Vendor.builder()
                .caption(createVendorCommand.getCaption())
                .build();
    }

}
