package com.lelakowski.ERPMetalbud.pi.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateColorCommand {

    @NotBlank
    private String oem;
    @NotBlank
    private String caption;

}
