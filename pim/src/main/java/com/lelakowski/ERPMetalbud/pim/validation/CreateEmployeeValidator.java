package com.lelakowski.ERPMetalbud.pim.validation;

import com.lelakowski.ERPMetalbud.pim.domain.repository.ProfessionRepository;
import com.lelakowski.ERPMetalbud.pim.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.pim.notification.NotFoundProfessionWithIdException;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateEmployeeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEmployeeValidator {

    private final ProfessionRepository professionRepository;

    public void validate(CreateEmployeeCommand createEmployeeCommand) {
        if (createEmployeeCommand == null)
            throw new IllegalCommandContentException(CreateEmployeeCommand.class.getSimpleName());

        validateProfessionExisting(createEmployeeCommand.getProfessionId());
    }

    private void validateProfessionExisting(Long professionId) {
        if (!professionRepository.existsById(professionId)) {
            throw new NotFoundProfessionWithIdException(professionId);
        }
    }
}
