package com.lelakowski.ERPMetalbud.mi.validation;

import com.lelakowski.ERPMetalbud.common.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.mi.domain.repository.MaterialRepository;
import com.lelakowski.ERPMetalbud.mi.notification.NotFoundMaterialWithIdException;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateBillOfMaterialItemValidator {

    private final MaterialRepository materialRepository;

    public void validate(CreateBillOfMaterialItemCommand createBillOfMaterialItemCommand) {
        if (createBillOfMaterialItemCommand == null)
            throw new IllegalCommandContentException(CreateBillOfMaterialItemCommand.class.getSimpleName());

        validateMaterialExisting(createBillOfMaterialItemCommand.getMaterialId());
    }

    private void validateMaterialExisting(Long materialId) {
        if (!materialRepository.existsById(materialId)) {
            throw new NotFoundMaterialWithIdException(materialId);
        }
    }
}
