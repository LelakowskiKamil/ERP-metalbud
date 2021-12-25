package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.pi.builder.ProductBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.domain.repository.BrandRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductDetailsRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.notification.NotFoundProductWithExternalNameException;
import com.lelakowski.ERPMetalbud.pi.validation.CreateProductValidator;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductBuilder productBuilder;
    private final ProductDetailsService productDetailsService;
    private final ProductDetailsRepository productDetailsRepository;
    private final PriceApiClient priceApiClient;
    private final BrandRepository brandRepository;
    private final VendorRepository vendorRepository;
    private final CreateProductValidator createProductValidator;

    private void sendCreatePriceCommand(CreateProductCommand createProductCommand) {
        priceApiClient.createPrice(createProductCommand.getPrice());
    }

    private Long createPrice(CreateProductCommand createProductCommand) {
        sendCreatePriceCommand(createProductCommand);
        return priceApiClient.getPriceIdByExternalName(createProductCommand.getPrice().getExternalName());
    }

    @Transactional
    @Override
    public Long saveProduct(CreateProductCommand createProductCommand) {
        createProductValidator.validate(createProductCommand);

        Long productDetailsId = productDetailsService.saveProductDetails(createProductCommand.getProductDetails());
        ProductDetails productDetails = productDetailsRepository.getOne(productDetailsId);

        Long priceId = createPrice(createProductCommand);

        Brand brand = brandRepository.getOne(createProductCommand.getBrandId());

        Vendor vendor = vendorRepository.getOne(createProductCommand.getVendorId());

        Product productToSave = productBuilder.from(
                createProductCommand.getCaption(),
                productDetails,
                priceId,
                vendor,
                brand
        );

        Product product = productRepository.save(productToSave);

        return product.getId();
    }

    @Override
    public Long getProductIdByExternalName(String externalName) {
        Optional<Long> productIdOpt = productRepository.findProductIdByExternalName(externalName);
        if (productIdOpt.isEmpty()) throw new NotFoundProductWithExternalNameException(externalName);
        return productIdOpt.get();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
