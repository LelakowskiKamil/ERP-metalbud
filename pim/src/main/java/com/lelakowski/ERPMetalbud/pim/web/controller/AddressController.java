package com.lelakowski.ERPMetalbud.pim.web.controller;

import com.lelakowski.ERPMetalbud.pim.domain.model.Address;
import com.lelakowski.ERPMetalbud.pim.service.AddressService;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateAddressCommand;
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
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<List> getAddresses() {
        return new ResponseEntity(addressService.getAddresses(), HttpStatus.OK);
    }


    @PostMapping(path = "/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Address> createAddress(@RequestBody @Valid CreateAddressCommand createAddressCommand) {
        addressService.saveAddress(createAddressCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
