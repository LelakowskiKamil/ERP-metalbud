package com.lelakowski.ERPMetalbud.pi.conventer;

import com.lelakowski.ERPMetalbud.common.domain.color.domain.model.Color;
import com.lelakowski.ERPMetalbud.common.domain.color.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductDetailsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDetailsConverter {

    private final ColorRepository colorRepository;
    private final ProductSpecificationRepository productSpecificationRepository;


    public ProductDetails from(CreateProductDetailsCommand createProductDetailsCommand) {
        if (createProductDetailsCommand == null) throw new IllegalArgumentException();

        //TODO validation does the productSpecification with the given id exist
        ProductSpecification productSpecification =
                productSpecificationRepository.getOne(createProductDetailsCommand.getProductSpecificationId());

        //TODO validation does the color with the given oem exist
        Color color = colorRepository.getColorByOEM(createProductDetailsCommand.getOem());

        return ProductDetails.builder()
                .productSpecification(productSpecification)
                .color(color)
                .build();
    }

}
