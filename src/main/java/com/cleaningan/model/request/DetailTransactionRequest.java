package com.cleaningan.model.request;

import com.cleaningan.entity.ShoeType;
import com.cleaningan.entity.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter @ToString
public class DetailTransactionRequest {

    @NotNull(message = "Units is required!")
    private Integer shoeUnits;

    @NotNull(message = "Shoe type is required")
    private Integer shoeTypeId;
}
