package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountBuilder {

    public Account from(String externalName, String username, String password, String email, Privileges privileges) {
        return Account.builder()
                .externalName(externalName)
                .username(username)
                .password(password)
                .email(email)
                .privileges(privileges)
                .build();
    }

}
