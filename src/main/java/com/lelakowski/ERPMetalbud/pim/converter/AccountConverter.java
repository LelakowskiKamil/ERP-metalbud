package com.lelakowski.ERPMetalbud.pim.converter;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import com.lelakowski.ERPMetalbud.pim.web.command.CreatePrivilegesCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountConverter {

    public Account from(CreateAccountCommand createAccountCommand) {
        if (createAccountCommand == null) throw new IllegalArgumentException();
        return Account.builder()
                .username(createAccountCommand.getUsername())
                .password(createAccountCommand.getPassword())
                .email(createAccountCommand.getEmail())
                .privileges(createAccountCommand.getPrivileges())
                .build();
    }

}
