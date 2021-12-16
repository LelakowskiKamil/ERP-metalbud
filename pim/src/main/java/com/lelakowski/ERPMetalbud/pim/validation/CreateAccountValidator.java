package com.lelakowski.ERPMetalbud.pim.validation;

import com.lelakowski.ERPMetalbud.common.notification.IllegalCommandContentException;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.notification.NotFoundPrivilegesWithIdException;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountValidator {

    private final PrivilegesRepository privilegesRepository;

    public void validate(CreateAccountCommand createAccountCommand) {
        if (createAccountCommand == null)
            throw new IllegalCommandContentException(CreateAccountCommand.class.getSimpleName());

        validatePrivilegesExisting(createAccountCommand.getPrivilegesId());
    }

    private void validatePrivilegesExisting(Long privilegesId) {
        if (!privilegesRepository.existsById(privilegesId)) {
            throw new NotFoundPrivilegesWithIdException(privilegesId);
        }
    }
}
