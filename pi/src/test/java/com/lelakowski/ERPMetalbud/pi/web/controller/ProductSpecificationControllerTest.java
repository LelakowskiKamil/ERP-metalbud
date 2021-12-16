package com.lelakowski.ERPMetalbud.pi.web.controller;

import com.google.gson.Gson;
import com.lelakowski.ERPMetalbud.ProductInventoryApplication;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimension;
import com.lelakowski.ERPMetalbud.common.dimension.domain.model.Dimensions;
import com.lelakowski.ERPMetalbud.common.dimension.domain.repository.DimensionRepository;
import com.lelakowski.ERPMetalbud.common.dimension.domain.repository.DimensionsRepository;
import com.lelakowski.ERPMetalbud.common.dimension.web.command.CreateDimensionsCommand;
import com.lelakowski.ERPMetalbud.pi.domain.model.ProductSpecification;
import com.lelakowski.ERPMetalbud.pi.domain.repository.ProductSpecificationRepository;
import com.lelakowski.ERPMetalbud.pi.web.command.CreateProductSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductInventoryApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductSpecificationControllerTest {

    @Autowired
    ProductSpecificationRepository productSpecificationRepository;

    @Autowired
    DimensionsRepository dimensionsRepository;

    @Autowired
    DimensionRepository dimensionRepository;

    @Autowired
    MockMvc mockMvc;

    String endpoint = "/productspecifications";

    @DisplayName("- should got product specification view on GET request")
    @Test
    void test1() throws Exception {
        Dimension dimensionHeight = new Dimension(1.5, "mm");
        Dimension dimensionWidth = new Dimension(1.5, "mm");
        Dimension dimensionLength = new Dimension(1.5, "mm");
        dimensionRepository.save(dimensionHeight);
        dimensionRepository.save(dimensionWidth);
        dimensionRepository.save(dimensionLength);
        Dimensions dimensions = new Dimensions("testDimension", dimensionHeight, dimensionWidth, dimensionLength);
        dimensionsRepository.save(dimensions);
        ProductSpecification productSpecification = new ProductSpecification("testProductSpec", dimensions);

        String expectedContent = "[{\"id\":1,\"caption\":\"testProductSpec\",\"dimensions\":{\"id\":1,\"caption\":\"testDimension\",\"height\":{\"id\":1,\"value\":1.5,\"unit\":\"mm\"},\"width\":{\"id\":2,\"value\":1.5,\"unit\":\"mm\"},\"length\":{\"id\":3,\"value\":1.5,\"unit\":\"mm\"}}}]";
        productSpecificationRepository.save(productSpecification);
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


    @Test
    @DisplayName("- should create product specification when data is correct")
    void test2() throws Exception {
        CreateDimensionsCommand dimensions = new CreateDimensionsCommand("testDimension", 1.5, 1.5, 1.5, "mm");
        CreateProductSpecification command = new CreateProductSpecification("testProductSpec", dimensions);
        Gson gson = new Gson();
        String json = gson.toJson(command);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String expectedContent = "[{\"id\":1,\"caption\":\"testProductSpec\",\"dimensions\":{\"id\":1,\"caption\":\"testDimension\",\"height\":{\"id\":1,\"value\":1.5,\"unit\":\"mm\"},\"width\":{\"id\":3,\"value\":1.5,\"unit\":\"mm\"},\"length\":{\"id\":2,\"value\":1.5,\"unit\":\"mm\"}}}]";
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    @DisplayName("- should throw error when data is incorrect correct")
    void test3() throws Exception {
        CreateDimensionsCommand dimensions = new CreateDimensionsCommand("test", -1, 1, 0, "mm");
        CreateProductSpecification command = new CreateProductSpecification("test", dimensions);

        Gson gson = new Gson();
        String json = gson.toJson(command);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}