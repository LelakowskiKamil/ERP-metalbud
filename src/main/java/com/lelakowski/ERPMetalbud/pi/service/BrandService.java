package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateVendorCommand;

import java.util.List;

public interface BrandService {

     void saveBrand(CreateBrandCommand createBrandCommand);

     List<Brand> getBrands();
}
