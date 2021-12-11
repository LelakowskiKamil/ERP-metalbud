package com.lelakowski.ERPMetalbud.pi.web.command;

import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    @Valid
    @NotNull
    private List<CreateBillOfMaterialItemCommand> billOfMaterials;
    @Min(0)
    private double priceValue;
    @NotBlank
    private String priceCurrency;


}
