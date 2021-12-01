package com.lelakowski.ERPMetalbud.pi.service;

import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.domain.repository.BillOfMaterialItemRepository;
import com.lelakowski.ERPMetalbud.mi.service.BillOfMaterialItemService;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pe.service.PriceService;
import com.lelakowski.ERPMetalbud.pi.builder.ProductBuilder;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.domain.repository.BrandRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductDetailsRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.validation.CreateProductValidator;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductBuilder productBuilder;
    private final ProductDetailsService productDetailsService;
    private final ProductDetailsRepository productDetailsRepository;
    private final PriceRepository priceRepository;
    private final PriceService priceService;
    private final BillOfMaterialItemService billOfMaterialItemService;
    private final BillOfMaterialItemRepository billOfMaterialItemRepository;
    private final BrandRepository brandRepository;
    private final VendorRepository vendorRepository;
    private final CreateProductValidator createProductValidator;

    @Transactional
    @Override
    public Long saveProduct(CreateProductCommand createProductCommand) {
        createProductValidator.validate(createProductCommand);

        Long productDetailsId = productDetailsService.saveProductDetails(createProductCommand.getProductDetails());
        ProductDetails productDetails = productDetailsRepository.getOne(productDetailsId);

        Long priceId = priceService.savePrice(createProductCommand.getPriceValue(), createProductCommand.getPriceCurrency());
        Price price = priceRepository.getOne(priceId);

        List<Long> createdBOMIds = createProductCommand.getBillOfMaterials().stream()
                .map(billOfMaterialItemService::saveBillOfMaterialItem)
                .collect(Collectors.toList());
        List<BillOfMaterialItem> createdBOM = billOfMaterialItemRepository.findAllById(createdBOMIds);

        Brand brand = brandRepository.getOne(createProductCommand.getBrandId());

        Vendor vendor = vendorRepository.getOne(createProductCommand.getVendorId());

        Product productToSave = productBuilder.from(
                createProductCommand.getCaption(),
                productDetails,
                price,
                createdBOM,
                vendor,
                brand
        );

        Product product = productRepository.save(productToSave);
        saveReferences(product, createdBOM, vendor, brand, productDetails, price);

        return product.getId();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    private void saveReferences(
            Product productReference,
            List<BillOfMaterialItem> createdBOM,
            Vendor vendor,
            Brand brand,
            ProductDetails productDetails,
            Price price
    ) {
        createdBOM.forEach(bom -> bom.setProduct(productReference));
        vendor.addToProductList(productReference);
        brand.addToProductList(productReference);
        price.addToProductList(productReference);
        productDetails.addToProductList(productReference);
    }
}
