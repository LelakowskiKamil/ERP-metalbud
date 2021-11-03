package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.service.AccountService;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
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
@RequestMapping("/")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/accounts")
    public ResponseEntity<List> getAccounts() {
        return new ResponseEntity(accountService.getAccounts(), HttpStatus.OK);
    }


    @PostMapping(path = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Account> createCustomer(@RequestBody Account toCreate) {
        accountService.saveAccount(toCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
