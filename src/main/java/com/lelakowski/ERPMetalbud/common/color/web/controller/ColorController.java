package com.lelakowski.ERPMetalbud.common.color.web.controller;

import com.lelakowski.ERPMetalbud.common.color.domain.service.ColorService;
import com.lelakowski.ERPMetalbud.common.color.web.command.CreateColorCommand;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/colors")
    public ResponseEntity<List> getColors() {
        return new ResponseEntity<>(colorService.getColors(), HttpStatus.OK);
    }

    @PostMapping(path = "/colors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Material> createColor(@RequestBody @Valid CreateColorCommand createColorCommand) {
        colorService.saveColor(createColorCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
