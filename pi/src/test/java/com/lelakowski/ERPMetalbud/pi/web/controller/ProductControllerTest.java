package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.ProductInventoryApplication;
import com.lelakowski.ERPMetalbud.pi.domain.model.*;
import com.lelakowski.ERPMetalbud.pi.domain.repository.*;
import com.lelakowski.ERPMetalbud.pi.service.PriceApiClient;
import com.lelakowski.ERPMetalbud.pi.web.command.CreatePiPriceCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductCommand;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductDetailsCommand;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductInventoryApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductControllerTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    DimensionsRepository dimensionsRepository;
    @Autowired
    DimensionRepository dimensionRepository;
    @Autowired
    VendorRepository vendorRepository;

    @MockBean
    PriceApiClient priceApiClient;
    @Autowired
    MockMvc mockMvc;

    String endpoint = "/products";

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("- should got product view on GET request")
    @Test
    void test1() throws Exception {
        Color color = new Color("Red", "Czerwony");
        colorRepository.save(color);
        Dimension dimensionHeight = new Dimension(1.5, "mm");
        Dimension dimensionWidth = new Dimension(1.5, "mm");
        Dimension dimensionLength = new Dimension(1.5, "mm");
        dimensionRepository.save(dimensionHeight);
        dimensionRepository.save(dimensionWidth);
        dimensionRepository.save(dimensionLength);
        Dimensions dimensions = new Dimensions("testDimension", dimensionHeight, dimensionWidth, dimensionLength);
        dimensionsRepository.save(dimensions);
        ProductSpecification productSpecification = new ProductSpecification("testProductSpec", dimensions);
        productSpecificationRepository.save(productSpecification);
        ProductDetails productDetails = new ProductDetails(color, productSpecification);
        productDetailsRepository.save(productDetails);
        Vendor vendor = new Vendor("vendor");
        vendorRepository.save(vendor);
        Brand brand = new Brand("brand");
        brandRepository.save(brand);
        Product product = new Product("product", productDetails, vendor, brand, 1L);
        productRepository.save(product);

        String expectedContent = "[{\"id\":1,\"externalName\":\"product\",\"productDetails\":{\"id\":1,\"color\":{\"id\":1,\"externalName\":\"Czerwony\",\"oem\":\"Red\"},\"productSpecification\":{\"id\":1,\"externalName\":\"testProductSpec\",\"dimensions\":{\"id\":1,\"caption\":\"testDimension\",\"height\":{\"id\":1,\"value\":1.5,\"unit\":\"mm\"},\"width\":{\"id\":2,\"value\":1.5,\"unit\":\"mm\"},\"length\":{\"id\":3,\"value\":1.5,\"unit\":\"mm\"}}}},\"vendor\":{\"id\":1,\"externalName\":\"vendor\"},\"brand\":{\"id\":1,\"externalName\":\"brand\"},\"priceId\":1}]";

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


    @Test
    @DisplayName("- should create product when data is correct")
    void test2() throws Exception {
        Color color = new Color("Red", "Czerwony");
        colorRepository.save(color);
        Dimension dimensionHeight = new Dimension(1.5, "mm");
        Dimension dimensionWidth = new Dimension(1.5, "mm");
        Dimension dimensionLength = new Dimension(1.5, "mm");
        dimensionRepository.save(dimensionHeight);
        dimensionRepository.save(dimensionWidth);
        dimensionRepository.save(dimensionLength);
        Dimensions dimensions = new Dimensions("testDimension", dimensionHeight, dimensionWidth, dimensionLength);
        dimensionsRepository.save(dimensions);
        ProductSpecification productSpecification = new ProductSpecification("testProductSpec", dimensions);
        productSpecificationRepository.save(productSpecification);
        Vendor vendor = new Vendor("vendor");
        vendorRepository.save(vendor);
        Brand brand = new Brand("brand");
        brandRepository.save(brand);

        CreateProductDetailsCommand createProductDetailsCommand = new CreateProductDetailsCommand(
                "productDetails", "Red", 1L
        );
        CreatePiPriceCommand price = new CreatePiPriceCommand("price1", 1.5, "PLN");
        CreateProductCommand createProductCommand = new CreateProductCommand("product", createProductDetailsCommand, 1L, 1L, price);

        ResponseEntity<ResponseEntity> responseEntity = Mockito.mock(ResponseEntity.class);
        Mockito.when(priceApiClient.createPrice(any(CreatePiPriceCommand.class))).thenReturn(responseEntity);
        Mockito.when(priceApiClient.getPriceIdByExternalName(any())).thenReturn(1L);

        Gson gson = new Gson();
        String json = gson.toJson(createProductCommand);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String expectedContent = "[{\"id\":1,\"externalName\":\"product\",\"productDetails\":{\"id\":1,\"color\":{\"id\":1,\"externalName\":\"Czerwony\",\"oem\":\"Red\"},\"productSpecification\":{\"id\":1,\"externalName\":\"testProductSpec\",\"dimensions\":{\"id\":1,\"caption\":\"testDimension\",\"height\":{\"id\":1,\"value\":1.5,\"unit\":\"mm\"},\"width\":{\"id\":2,\"value\":1.5,\"unit\":\"mm\"},\"length\":{\"id\":3,\"value\":1.5,\"unit\":\"mm\"}}}},\"vendor\":{\"id\":1,\"externalName\":\"vendor\"},\"brand\":{\"id\":1,\"externalName\":\"brand\"},\"priceId\":1}]";

        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));

    }

    @Test
    @DisplayName("- should throw error when data is incorrect correct - nonexistent vendor")
    void test3() throws Exception {
        Color color = new Color("Red", "Czerwony");
        colorRepository.save(color);
        Dimension dimensionHeight = new Dimension(1.5, "mm");
        Dimension dimensionWidth = new Dimension(1.5, "mm");
        Dimension dimensionLength = new Dimension(1.5, "mm");
        dimensionRepository.save(dimensionHeight);
        dimensionRepository.save(dimensionWidth);
        dimensionRepository.save(dimensionLength);
        Dimensions dimensions = new Dimensions("testDimension", dimensionHeight, dimensionWidth, dimensionLength);
        dimensionsRepository.save(dimensions);
        ProductSpecification productSpecification = new ProductSpecification("testProductSpec", dimensions);
        productSpecificationRepository.save(productSpecification);
        Vendor vendor = new Vendor("vendor");
        vendorRepository.save(vendor);
        Brand brand = new Brand("brand");
        brandRepository.save(brand);

        CreateProductDetailsCommand createProductDetailsCommand = new CreateProductDetailsCommand(
                "productDetails", "Red", 1L
        );
        CreatePiPriceCommand price = new CreatePiPriceCommand("price1", 1.5, "PLN");
        CreateProductCommand createProductCommand = new CreateProductCommand("product", createProductDetailsCommand, 10L, 1L, price);

        ResponseEntity<ResponseEntity> responseEntity = Mockito.mock(ResponseEntity.class);
        Mockito.when(priceApiClient.createPrice(any(CreatePiPriceCommand.class))).thenReturn(responseEntity);
        Mockito.when(priceApiClient.getPriceIdByExternalName(any())).thenReturn(1L);

        Gson gson = new Gson();
        String json = gson.toJson(createProductCommand);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}