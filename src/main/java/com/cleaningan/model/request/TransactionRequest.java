package com.cleaningan.model.request;

import com.cleaningan.entity.DetailTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter @ToString
public class TransactionRequest {

    @NotBlank(message = "Date is required!")
    private String entryDate;

    @NotBlank(message = "Customer is required!")
    private String customerEmail;

    @NotNull(message = "Packet-type is required!")
    private Integer packetType;

    @NotEmpty(message = "Units is required!")
    @JsonBackReference
    private List<DetailTransactionRequest> detailTransactions;
}
