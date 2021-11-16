package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.service.AccountService;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List> getAccounts() {
        return new ResponseEntity(accountService.getAccounts(), HttpStatus.OK);
    }


    @PostMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Account> createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        accountService.saveAccount(createAccountCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
