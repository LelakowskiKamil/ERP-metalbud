package com.lelakowski.ERPMetalbud.pim.service;

import com.lelakowski.ERPMetalbud.pim.builder.AccountBuilder;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.PrivilegesRepository;
import com.lelakowski.ERPMetalbud.pim.validation.CreateAccountValidator;
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
    private final CreateAccountValidator createAccountValidator;

    @Transactional
    @Override
    public Long saveAccount(CreateAccountCommand createAccountCommand) {
        createAccountValidator.validate(createAccountCommand);
        Privileges privileges = privilegesRepository.getOne(createAccountCommand.getPrivilegesId());

        Account accountToSave = accountBuilder.from(
                createAccountCommand.getUsername(),
                createAccountCommand.getPassword(),
                createAccountCommand.getEmail(),
                privileges
        );

        Account account = accountRepository.save(accountToSave);
        return account.getId();
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

}
