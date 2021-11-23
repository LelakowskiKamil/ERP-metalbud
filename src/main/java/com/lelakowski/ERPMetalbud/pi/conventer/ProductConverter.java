package com.lelakowski.ERPMetalbud.pi.conventer;

import com.lelakowski.ERPMetalbud.mi.converter.BillOfMaterialItemConverter;
import com.lelakowski.ERPMetalbud.mi.domain.model.BillOfMaterialItem;
import com.lelakowski.ERPMetalbud.mi.service.BillOfMaterialItemService;
import com.lelakowski.ERPMetalbud.pe.converter.PriceConverter;
import com.lelakowski.ERPMetalbud.pe.domain.model.Price;
import com.lelakowski.ERPMetalbud.pe.domain.repository.PriceRepository;
import com.lelakowski.ERPMetalbud.pe.service.PriceService;
import com.lelakowski.ERPMetalbud.pi.domain.model.Brand;
import com.lelakowski.ERPMetalbud.pi.domain.model.Product;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductDetails;
import com.lelakowski.ERPMetalbud.pi.domain.model.Vendor;
import com.lelakowski.ERPMetalbud.pi.domain.repository.BrandRepository;
import com.lelakowski.ERPMetalbud.pi.domain.repository.VendorRepository;
import com.lelakowski.ERPMetalbud.pi.service.ProductDetailsService;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final BrandRepository brandRepository;
    private final VendorRepository vendorRepository;
    private final ProductDetailsService productDetailsService;
    private final ProductDetailsConverter productDetailsConverter;
    private final BillOfMaterialItemConverter billOfMaterialItemConverter;
    private final BillOfMaterialItemService billOfMaterialItemService;
    private final PriceService priceService;
    private final PriceRepository priceRepository;
    private final PriceConverter priceConverter;

    public Product from(CreateProductCommand createProductCommand) {
        if (createProductCommand == null) throw new IllegalArgumentException();

        productDetailsService.saveProductDetails(createProductCommand.getProductDetails());
        ProductDetails productDetails = productDetailsConverter.from(createProductCommand.getProductDetails());


        Brand brand = brandRepository.getOne(createProductCommand.getBrandId());
        Vendor vendor = vendorRepository.getOne(createProductCommand.getVendorId());
        priceService.savePrice(createProductCommand.getPriceValue(), createProductCommand.getPriceCurrency());
        Price price = priceConverter.from(createProductCommand.getPriceValue(), createProductCommand.getPriceCurrency());

        createProductCommand.getBillOfMaterials()
                        .forEach(billOfMaterialItemService::saveBillOfMaterialItem);
        List<BillOfMaterialItem> billOfMaterialItems = createProductCommand.getBillOfMaterials().stream()
                .map(billOfMaterialItemConverter::from)
                .collect(Collectors.toList());

        return Product.builder()
                .productDetails(productDetails)
                .caption(createProductCommand.getCaption())
                .brand(brand)
                .vendor(vendor)
                .price(price)
                .billOfMaterialItems(billOfMaterialItems)
                .build();
    }

}
