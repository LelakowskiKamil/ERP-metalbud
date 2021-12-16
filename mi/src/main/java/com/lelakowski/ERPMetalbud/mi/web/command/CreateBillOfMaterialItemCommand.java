package com.lelakowski.ERPMetalbud.mi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBillOfMaterialItemCommand {

    @NotNull
    private Long materialId;
    @Min(value = 0)
    private Double quantity;


}
