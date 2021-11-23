package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.conventer.ProductConverter;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService  {

     private final ProductRepository productRepository;
     private final ProductConverter productConverter;

     @Override
     public void saveProduct(CreateProductCommand createProductCommand) {
          Product productToSave = productConverter.from(createProductCommand);
          productRepository.save(productToSave);
     }

     @Override
     public List<Product> getProducts() {
          return productRepository.findAll();
     }
}
