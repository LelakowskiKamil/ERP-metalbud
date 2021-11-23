package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateBrandCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductDetailsCommand;

import java.util.List;

public interface ProductDetailsService {

     void saveProductDetails(CreateProductDetailsCommand createProductDetailsCommand);

     List<ProductDetails> getProductDetails();
}
