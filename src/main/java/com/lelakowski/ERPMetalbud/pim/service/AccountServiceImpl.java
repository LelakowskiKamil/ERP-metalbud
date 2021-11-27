package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.builder.AccountBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountBuilder accountBuilder;
    private final PrivilegesRepository privilegesRepository;

    @Transactional
    @Override
    public Long saveAccount(CreateAccountCommand createAccountCommand) {
        Privileges privileges = privilegesRepository.getOne(createAccountCommand.getPrivilegesId());

        Account accountToSave = accountBuilder.from(
                createAccountCommand.getUsername(),
                createAccountCommand.getPassword(),
                createAccountCommand.getEmail(),
                privileges
        );

        Account account = accountRepository.save(accountToSave);
        saveReferences(account,privileges);

        return account.getId();
    }

    public List<Account> getAccounts() {
     return accountRepository.findAll();
    }

    private void saveReferences(Account accountReference, Privileges privileges){
        privileges.addToAccountList(accountReference);
    }
}
