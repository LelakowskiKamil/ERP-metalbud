package com.lelakowski.ERPMetalbud.pim.builder;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AccountBuilder {

    public Account from(String username, String password, String email, Privileges privileges) {
        return Account.builder()
                .username(username)
                .password(password)
                .email(email)
                .privileges(privileges)
                .customers(new ArrayList<>())
                .build();
    }

}
