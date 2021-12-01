package com.lelakowski.ERPMetalbud.pi.web.command;

import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialItemCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    private double priceValue;
    private String priceCurrency;


}
