package com.lelakowski.ERPMetalbud.mi.web.controller;

import com.lelakowski.ERPMetalbud.mi.domain.model.Material;
import com.lelakowski.ERPMetalbud.mi.service.MaterialService;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateMaterialCommand;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.service.OrderItemService;
import com.lelakowski.ERPMetalbud.om.service.OrderService;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/materials")
    public ResponseEntity<List> getMaterials() {
        return new ResponseEntity<>(materialService.getMaterials(), HttpStatus.OK);
    }


    @PostMapping(path = "/materials", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Material> createMaterial(@RequestBody CreateMaterialCommand createMaterialCommand) {
        materialService.saveMaterial(createMaterialCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
