package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.service.ProfessionService;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateProfessionCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfessionController {

    private final ProfessionService professionService;

    @GetMapping("/professions")
    public ResponseEntity<List> getProfessions() {
        return new ResponseEntity(professionService.getProfessions(), HttpStatus.OK);
    }

    @PostMapping(path = "/professions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createPrivileges(@RequestBody @Valid CreateProfessionCommand createProfessionCommand) {
        professionService.saveProfession(createProfessionCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
