package com.lelakowski.ERPMetalbud.mi.converter;

import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;
import com.lelakowski.ERPMetalbud.om.conventer.OrderItemConverter;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MaterialConverter {

    public Material from(CreateMaterialCommand createMaterialCommand) {
        if (createMaterialCommand == null) throw new IllegalArgumentException();

        return Material.builder()
                .caption(createMaterialCommand.getCaption())
                .build();
    }

}
