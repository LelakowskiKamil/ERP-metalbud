package com.lelakowski.ERPMetalbud.pi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePiPriceCommand {

    @NotBlank
    private String externalName;
    @Min(value = 0)
    private double value;
    @NotBlank
    private String currency;

}
