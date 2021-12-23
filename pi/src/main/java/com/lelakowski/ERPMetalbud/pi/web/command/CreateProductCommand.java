package com.lelakowski.ERPMetalbud.pi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {
    @NotBlank
    @Size(min = 2, max = 50)
    private String caption;
    @Valid
    @NotNull
    private CreateProductDetailsCommand productDetails;
    @NotNull
    private Long vendorId;
    @NotNull
    private Long brandId;
    @NotNull
    CreatePiPriceCommand price;


}
