package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.lelakowski.ERPMetalbud.pi.service.ProductService;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List> getProducts() {
        return new ResponseEntity(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/externalName/{externalName}")
    public ResponseEntity<List> productIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity(productService.getProductIdByExternalName(externalName), HttpStatus.OK);
    }


    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createProduct(@RequestBody @Valid CreateProductCommand createProductCommand) {
        productService.saveProduct(createProductCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
