package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductSpecification;

import java.util.List;

public interface ProductSpecificationService {

     Long saveProductSpecification(CreateProductSpecification createProductSpecification);

     List<ProductSpecification> getProductSpecifications();
}
