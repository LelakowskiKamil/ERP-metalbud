package com.lelakowski.ERPMetalbud.mi.web.controller;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterials;
import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.service.BillOfMaterialsService;
import com.lelakowski.ERPMetalbud.mi.service.MaterialService;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;
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
public class BillOfMaterialsController {

    private final BillOfMaterialsService billOfMaterialsService;

    @GetMapping("/billofmaterials")
    public ResponseEntity<List> getBillOfMaterials() {
        return new ResponseEntity<>(billOfMaterialsService.getBillOfMaterials(), HttpStatus.OK);
    }


    @PostMapping(
            path = "/billofmaterials",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<BillOfMaterials> createBillOfMaterials(
            @RequestBody CreateBillOfMaterialsCommand createBillOfMaterialsCommand
    ) {
        billOfMaterialsService.saveBillOfMaterial(createBillOfMaterialsCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
