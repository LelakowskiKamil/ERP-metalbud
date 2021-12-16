package com.lelakowski.ERPMetalbud.pi.web.command;

import com.lelakowski.ERPMetalbud.common.dimension.web.command.CreateDimensionsCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductSpecification {
    @NotBlank
    @Size(min = 2, max = 50)
    private String caption;
    @Valid
    @NotNull
    private CreateDimensionsCommand dimensions;

}