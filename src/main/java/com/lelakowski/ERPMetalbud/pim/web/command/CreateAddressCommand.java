package com.lelakowski.ERPMetalbud.pim.web.command;

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
public class CreateAddressCommand {
    @NotBlank
    @Size(min = 2, max = 255)
    private String city;
    @NotBlank
    @Size(min = 5, max = 30)
    private String postalCode;
    @NotBlank
    @Size(min = 2, max = 100)
    private String state;
    @NotBlank
    @Size(min = 2, max = 100)
    private String country;
}
