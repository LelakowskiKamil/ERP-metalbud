package com.lelakowski.ERPMetalbud.pim.validation;

import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEmployeeValidator {

    private final PrivilegesRepository privilegesRepository;
    private final PriceRepository priceRepository;

    public void validate(CreateEmployeeCommand createEmployeeCommand) {
        if (createEmployeeCommand == null) throw new IllegalArgumentException(); //TODO notification

        validatePrivilegesExisting(createEmployeeCommand.getProfessionId());
        validatePriceExisting(createEmployeeCommand.getPriceId());
    }

    private void validatePrivilegesExisting(Long privilegesId) {
        if (!privilegesRepository.existsById(privilegesId)) {
            throw new IllegalArgumentException("Nie ma takiego privileges"); //TODO notification
        }
    }

    private void validatePriceExisting(Long priceId) {
        if (!priceRepository.existsById(priceId)) {
            throw new IllegalArgumentException("Nie ma takiego cena"); //TODO notification
        }
    }
}
