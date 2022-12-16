package com.cleaningan.controller;

import com.cleaningan.constant.MessageResponse;
import com.cleaningan.entity.PacketType;
import com.cleaningan.entity.ShoeType;
import com.cleaningan.model.request.PacketTypeRequest;
import com.cleaningan.model.request.ShoeTypeRequest;
import com.cleaningan.model.response.SuccessResponse;
import com.cleaningan.service.IPacketTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/packet-types")
@Validated
public class PacketTypeController {

    private final ModelMapper modelMapper;

    private final IPacketTypeService packetTypeService;

    public PacketTypeController(ModelMapper modelMapper, IPacketTypeService packetTypeService) {
        this.modelMapper = modelMapper;
        this.packetTypeService = packetTypeService;
    }

    @GetMapping
    public ResponseEntity getAllPacketType() {
        List<PacketType> packetTypes = packetTypeService.findAll();
        return ResponseEntity.ok(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA + "Packet Type!", packetTypes));
    }

    @PostMapping
    public ResponseEntity addPacketType(@Valid @RequestBody PacketTypeRequest packetTypeRequest) throws Exception {
        PacketType packetType = modelMapper.map(packetTypeRequest, PacketType.class);
        PacketType newPacketType = packetTypeService.save(packetType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(MessageResponse.SUCCESS_CREATE + "Packet Type!", newPacketType));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOnePacketType(@PathVariable("id") Integer id) {
        PacketType packetType = packetTypeService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA + "Packet Type!", packetType));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOnePacketType(@PathVariable("id") Integer id, @Valid @RequestBody PacketTypeRequest packetTypeRequest) {
        PacketType packetType = modelMapper.map(packetTypeRequest, PacketType.class);
        PacketType updatePacketType = packetTypeService.update(id, packetType);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_UPDATE_DATA + "Packet Type!", updatePacketType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeOnePacketType(@PathVariable("id") Integer id) {
        PacketType packetType = packetTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_DELETE_DATA + "Packet Type!", packetType));
    }
}
