package com.lelakowski.ERPMetalbud.pi.web.command;

import com.lelakowski.ERPMetalbud.common.web.command.CreateDimensionsCommand;
import com.lelakowski.ERPMetalbud.mi.web.command.CreateBillOfMaterialsCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductSpecification {
    @NotBlank
    @Size(min = 2, max = 50)
    private String caption;
    @NotBlank
    private CreateDimensionsCommand dimensions;






}
