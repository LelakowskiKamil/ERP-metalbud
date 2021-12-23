package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.service.AccountService;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List> getAccounts() {
        return new ResponseEntity(accountService.getAccounts(), HttpStatus.OK);
    }

    @GetMapping("/accounts/externalName/{externalName}")
    public ResponseEntity<List> accountIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity(accountService.getAccountIdByExternalName(externalName), HttpStatus.OK);
    }


    @PostMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Account> createAccount(@RequestBody @Valid CreateAccountCommand createAccountCommand) {
        accountService.saveAccount(createAccountCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
