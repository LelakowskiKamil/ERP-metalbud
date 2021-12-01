package com.lelakowski.ERPMetalbud.mi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBillOfMaterialItemCommand {

    @NotBlank
    private Long materialId;
    @NotNull
    private Double quantity;


}
