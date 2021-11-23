package com.lelakowski.ERPMetalbud.pi.web.command;

import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import com.lelakowski.ERPMetalbud.pe.web.command.CreatePriceCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotBlank
    private CreateProductDetailsCommand productDetails;
    private Long vendorId;
    private Long brandId;
    private List<CreateBillOfMaterialItemCommand> billOfMaterials;
    private Long productSpecificationId;
    private double priceValue;
    private String priceCurrency;






}
