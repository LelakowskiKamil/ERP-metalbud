package com.lelakowski.ERPMetalbud.pi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDetailsCommand {
    @NotBlank
    @Size(min = 2, max = 50)
    private String caption;
    @NotBlank
    private String oem;
    @NotNull
    private Long productSpecificationId;
}
