package com.lelakowski.ERPMetalbud.mi.web.controller;

import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.service.MaterialService;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;
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
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/materials")
    public ResponseEntity<List> getMaterials() {
        return new ResponseEntity<>(materialService.getMaterials(), HttpStatus.OK);
    }

    @PostMapping(path = "/materials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Material> createMaterial(@RequestBody @Valid CreateMaterialCommand createMaterialCommand) {
        materialService.saveMaterial(createMaterialCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
