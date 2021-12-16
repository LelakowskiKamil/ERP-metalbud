package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.service.BrandService;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<List> getBrands() {
        return new ResponseEntity(brandService.getBrands(), HttpStatus.OK);
    }


    @PostMapping(path = "/brands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Brand> createBrand(@RequestBody @Valid CreateBrandCommand createBrandCommand) {
        brandService.saveBrand(createBrandCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
