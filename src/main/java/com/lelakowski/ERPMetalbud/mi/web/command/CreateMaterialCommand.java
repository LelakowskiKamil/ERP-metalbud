package com.lelakowski.ERPMetalbud.mi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialCommand {

    @NotBlank
    private String caption;

}
