package com.lelakowski.ERPMetalbud.pi.validation;

import com.lelakowski.ERPMetalbud.common.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.mi.validation.CreateBillOfMaterialItemValidator;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import com.lelakowski.ERPMetalbud.pi.domain.repository.BrandRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundBrandWithIdException;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundVendorWithIdException;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductValidator {

    private final VendorRepository vendorRepository;
    private final BrandRepository brandRepository;
    private final CreateProductDetailsValidator createProductDetailsValidator;
    private final CreateBillOfMaterialItemValidator createBillOfMaterialItemValidator;

    public void validate(CreateProductCommand createProductCommand) {
        if (createProductCommand == null)
            throw new IllegalCommandContentException(CreateProductCommand.class.getSimpleName());

        validateVendorExisting(createProductCommand.getVendorId());
        validateBrandExisting(createProductCommand.getBrandId());
        createProductCommand.getBillOfMaterials()
                .forEach(createBillOfMaterialItemValidator::validate);
        createProductDetailsValidator.validate(createProductCommand.getProductDetails());
    }

    private void validateVendorExisting(Long vendorId) {
        if (!vendorRepository.existsById(vendorId)) {
            throw new NotFoundVendorWithIdException(vendorId);
        }
    }

    private void validateBrandExisting(Long brandId) {
        if (!brandRepository.existsById(brandId)) {
            throw new NotFoundBrandWithIdException(brandId);
        }
    }
}
