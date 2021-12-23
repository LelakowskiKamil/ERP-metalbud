package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;

import java.util.List;

public interface AccountService {

    Long saveAccount(CreateAccountCommand createAccountCommand);

    List<Account> getAccounts();

    Long getAccountIdByExternalName(String externalName);

}
