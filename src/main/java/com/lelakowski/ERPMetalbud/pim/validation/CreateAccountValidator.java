package com.lelakowski.ERPMetalbud.pim.validation;

import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountValidator {

    private final PrivilegesRepository privilegesRepository;

    public void validate(CreateAccountCommand createAccountCommand) {
        if (createAccountCommand == null) throw new IllegalArgumentException(); //TODO notification

        validatePrivilegesExisting(createAccountCommand.getPrivilegesId());
    }

    private void validatePrivilegesExisting(Long privilegesId) {
        if (!privilegesRepository.existsById(privilegesId)) {
            throw new IllegalArgumentException("Nie ma takiego privileges"); //TODO notification
        }
    }
}
