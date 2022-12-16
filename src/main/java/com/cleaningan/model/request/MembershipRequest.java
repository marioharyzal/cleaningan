package com.cleaningan.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MembershipRequest {

    @NotBlank(message = "Membership name is required!")
    private String membershipName;

    @NotBlank(message = "Membership description is required!")
    private String membershipDescription;

    @NotNull(message = "Membership discount amount is required!")
    private Integer discountAmount;
}
