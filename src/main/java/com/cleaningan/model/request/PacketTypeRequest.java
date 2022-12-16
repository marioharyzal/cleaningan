package com.cleaningan.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PacketTypeRequest {

    @NotBlank(message = "Packet Name is required!")
    private String packetName;

    @NotBlank(message = "Packet description is required!")
    private String packetDescription;

    @NotNull(message = "Washing time is required!")
    private Integer washingTime;
}
