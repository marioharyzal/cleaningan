package com.cleaningan.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CustomerRequest {
    @NotBlank(message = "Customer email is required!")
    @Email(message = "Email must be valid!")
    private String customerEmail;

    @NotBlank(message = "Customer name is required!")
    private String customerName;

    @NotBlank(message = "Customer address is required!")
    private String customerAddress;

    @NotBlank(message = "Customer phone is required!")
    private String customerPhone;

    @NotNull(message = "Membership status is required!")
    private Integer membershipId;

    @NotBlank(message = "Customer password is required!")
    private String customerPassword;
}
