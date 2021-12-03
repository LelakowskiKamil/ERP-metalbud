package com.lelakowski.ERPMetalbud.pi.validation;

import com.lelakowski.ERPMetalbud.common.color.domain.repository.ColorRepository;
import com.lelakowski.ERPMetalbud.common.color.notification.NotFoundColorWithOEMException;
import com.lelakowski.ERPMetalbud.common.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundProductSpecificationWithIdException;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductDetailsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductDetailsValidator {

    private final ProductSpecificationRepository productSpecificationRepository;
    private final ColorRepository colorRepository;

    public void validate(CreateProductDetailsCommand createProductDetailsCommand) {
        if (createProductDetailsCommand == null)
            throw new IllegalCommandContentException(CreateProductDetailsCommand.class.getSimpleName());

        validateProductSpecificationExisting(createProductDetailsCommand.getProductSpecificationId());
        validateColorExisting(createProductDetailsCommand.getOem());
    }

    private void validateProductSpecificationExisting(Long productSpecificationId) {
        if (!productSpecificationRepository.existsById(productSpecificationId)) {
            throw new NotFoundProductSpecificationWithIdException(productSpecificationId);
        }
    }

    private void validateColorExisting(String oem) {
        if (colorRepository.getColorByOEM(oem).isEmpty()) {
            throw new NotFoundColorWithOEMException(oem);
        }
    }
}
