package com.cleaningan.controller;

import com.cleaningan.constant.MessageResponse;
import com.cleaningan.entity.ShoeType;
import com.cleaningan.model.request.ShoeTypeRequest;
import com.cleaningan.model.response.SuccessResponse;
import com.cleaningan.service.IShoeTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shoe-types")
@Validated
public class ShoeTypeController {

    private final ModelMapper modelMapper;

    private final IShoeTypeService shoeTypeService;

    public ShoeTypeController(ModelMapper modelMapper, IShoeTypeService shoeTypeService) {
        this.modelMapper = modelMapper;
        this.shoeTypeService = shoeTypeService;
    }

    @GetMapping
    public ResponseEntity getAllShoeType() {
        List<ShoeType> shoeTypes = shoeTypeService.findAll();
        return ResponseEntity.ok(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA + "Shoe Type!", shoeTypes));
    }

    @PostMapping
    public ResponseEntity addShoeType(@Valid @RequestBody ShoeTypeRequest shoeTypeRequest) throws Exception {
        ShoeType shoeTypeMap = modelMapper.map(shoeTypeRequest, ShoeType.class);
        ShoeType newShoeType = shoeTypeService.save(shoeTypeMap);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(MessageResponse.SUCCESS_CREATE + "Shoe Type!", newShoeType));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneShoeType(@PathVariable("id") Integer id) {
        ShoeType shoeType = shoeTypeService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA.toString() + "Shoe Type!", shoeType));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOneShoeType(@PathVariable("id") Integer id, @Valid @RequestBody ShoeTypeRequest shoeTypeRequest) {
        ShoeType shoeType = modelMapper.map(shoeTypeRequest, ShoeType.class);
        ShoeType updateShoeType = shoeTypeService.update(id, shoeType);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_UPDATE_DATA + "Shoe Type!", updateShoeType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeOneShoeType(@PathVariable("id") Integer id) {
        ShoeType shoeType = shoeTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_DELETE_DATA + "Shoe Type!", shoeType));
    }
}
