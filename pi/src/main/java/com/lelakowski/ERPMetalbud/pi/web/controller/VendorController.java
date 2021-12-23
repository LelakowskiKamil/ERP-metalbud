package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.service.VendorService;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;
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
public class VendorController {

    private final VendorService vendorService;

    @GetMapping("/vendors")
    public ResponseEntity<List> getVendors() {
        return new ResponseEntity(vendorService.getVendors(), HttpStatus.OK);
    }

    @GetMapping("/vendors/externalName/{externalName}")
    public ResponseEntity<List> vendorIdByExternalName(@PathVariable("externalName") String externalName) {
        return new ResponseEntity(vendorService.getVendorIdByExternalName(externalName), HttpStatus.OK);
    }

    @PostMapping(path = "/vendors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vendor> createVendor(@RequestBody @Valid CreateVendorCommand createVendorCommand) {
        vendorService.saveVendor(createVendorCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
