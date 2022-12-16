package com.cleaningan.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ShoeTypeRequest {

    @NotBlank(message = "Type name is required!")
    private String typeName;

    @NotBlank(message = "Type Description name is required!")
    private String typeDescription;

    @NotNull(message = "Cost is required!")
    private Integer cost;
}
