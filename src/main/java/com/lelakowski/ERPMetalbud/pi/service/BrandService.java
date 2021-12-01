package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;

import java.util.List;

public interface BrandService {

    Long saveBrand(CreateBrandCommand createBrandCommand);

    List<Brand> getBrands();
}
