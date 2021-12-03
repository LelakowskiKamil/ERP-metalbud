package com.lelakowski.ERPMetalbud.pim.validation;

import com.lelakowski.ERPMetalbud.common.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.notification.NotFoundPrivilegesWithIdException;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEmployeeValidator {

    private final PrivilegesRepository privilegesRepository;

    public void validate(CreateEmployeeCommand createEmployeeCommand) {
        if (createEmployeeCommand == null)
            throw new IllegalCommandContentException(CreateEmployeeCommand.class.getSimpleName());

        validatePrivilegesExisting(createEmployeeCommand.getProfessionId());
    }

    private void validatePrivilegesExisting(Long privilegesId) {
        if (!privilegesRepository.existsById(privilegesId)) {
            throw new NotFoundPrivilegesWithIdException(privilegesId);
        }
    }
}
