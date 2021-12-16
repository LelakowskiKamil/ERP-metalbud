package com.lelakowski.ERPMetalbud.common.dimension.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDimensionsCommand {

    @NotBlank
    private String caption;
    @Min(value = 0)
    private double height;
    @Min(value = 0)
    private double width;
    @Positive
    private double length;
    @NotBlank
    private String unit;

}
