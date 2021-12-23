package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.lelakowski.ERPMetalbud.pi.domain.model.Color;
import com.lelakowski.ERPMetalbud.pi.service.ColorService;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateColorCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/colors")
    public ResponseEntity<List> getColors() {
        return new ResponseEntity<>(colorService.getColors(), HttpStatus.OK);
    }

    @GetMapping("/colors/oem/{oem}")
    public ResponseEntity<List> colorIdByOem(@PathVariable("oem") String oem) {
        return new ResponseEntity(colorService.getColorIdByOem(oem), HttpStatus.OK);
    }

    @GetMapping("/colors/externalName/{externalName}")
    public ResponseEntity<List> colorIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity(colorService.getColorIdByExternalName(externalName), HttpStatus.OK);
    }

    @PostMapping(path = "/colors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Color> createColor(@RequestBody @Valid CreateColorCommand createColorCommand) {
        colorService.saveColor(createColorCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
