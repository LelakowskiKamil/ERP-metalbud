package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.service.AddressService;
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
@RequestMapping("/")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/addresses")
    public ResponseEntity<List> getAddresses() {
        return new ResponseEntity(addressService.getAddresses(), HttpStatus.OK);
    }


    @PostMapping(path = "/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Address> createAddress(@RequestBody Address toCreate) {
        addressService.saveAddress(toCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
