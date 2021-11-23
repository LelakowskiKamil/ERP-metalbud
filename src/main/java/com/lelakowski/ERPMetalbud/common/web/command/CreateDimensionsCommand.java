package com.lelakowski.ERPMetalbud.common.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDimensionsCommand {

    @NotBlank
    private String caption;
    @NotBlank
    private double height;
    @NotBlank
    private double width;
    @NotBlank
    private double length;
    @NotBlank
    private String unit;

}
