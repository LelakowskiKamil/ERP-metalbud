package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;

import java.util.List;

public interface ProductService {

    Long saveProduct(CreateProductCommand createProductCommand);

    List<Product> getProducts();

    Long getProductIdByExternalName(String externalName);
}
