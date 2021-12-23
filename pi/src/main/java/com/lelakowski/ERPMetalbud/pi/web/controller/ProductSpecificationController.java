package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.service.ProductSpecificationService;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ProductSpecificationController {

    private final ProductSpecificationService productSpecificationService;

    @GetMapping("/productspecifications")
    public ResponseEntity<List> getProductSpecifications() {
        return new ResponseEntity(productSpecificationService.getProductSpecifications(), HttpStatus.OK);
    }

    @GetMapping("/productspecifications/externalName/{externalName}")
    public ResponseEntity<List> productSpecificationsIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity(productSpecificationService.getProductSpecificationIdByExternalName(externalName), HttpStatus.OK);
    }


    @PostMapping(path = "/productspecifications", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Brand> createProductSpecification(@RequestBody @Valid CreateProductSpecification createProductSpecification) {
        productSpecificationService.saveProductSpecification(createProductSpecification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
