package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.service.PrivilegesService;
import com.lelakowski.ERPMetalbud.pim.web.command.CreatePrivilegesCommand;
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
public class PrivilegesController {

    private final PrivilegesService privilegesService;

    @GetMapping("/privileges")
    public ResponseEntity<List> getPrivileges() {
        return new ResponseEntity(privilegesService.getPrivileges(), HttpStatus.OK);
    }

    @PostMapping(path = "/privileges", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createPrivileges(@RequestBody CreatePrivilegesCommand createPrivilegesCommand) {
        privilegesService.savePrivileges(createPrivilegesCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
