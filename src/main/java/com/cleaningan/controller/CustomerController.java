package com.cleaningan.controller;

import com.cleaningan.constant.MessageResponse;
import com.cleaningan.entity.Customer;
import com.cleaningan.model.request.CustomerRequest;
import com.cleaningan.model.response.SuccessResponse;
import com.cleaningan.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    private final ModelMapper modelMapper;

    private final ICustomerService customerService;

    public CustomerController(ModelMapper modelMapper, ICustomerService customerService) {
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity getAllCustomer() {
        List<Customer> customers = customerService.findAll();
        return ResponseEntity.ok(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA + "Customers", customers));
    }

    @PostMapping
    public ResponseEntity addMembership(@Valid @RequestBody CustomerRequest customerRequest) throws Exception {
        Customer customerMap = modelMapper.map(customerRequest, Customer.class);
        Customer newCustomer = customerService.save(customerMap);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(MessageResponse.SUCCESS_CREATE + "Customers", newCustomer));
    }

    @GetMapping("/{email}")
    public ResponseEntity getOneMembership(@PathVariable("email") String email) {
        Customer oneCustomer = customerService.findOne(email);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_GET_DATA.toString() + "Customers", oneCustomer));
    }

    @PutMapping("/{email}")
    public ResponseEntity updateOneMembership(@PathVariable("email") String email, @Valid @RequestBody CustomerRequest customerRequest) {
        Customer customerMap = modelMapper.map(customerRequest, Customer.class);
        Customer updateCustomer = customerService.update(email, customerMap);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_UPDATE_DATA + "Customers", updateCustomer));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity removeOneMembership(@PathVariable("email") String email) {
        Customer deletedCustomer = customerService.delete(email);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(MessageResponse.SUCCESS_DELETE_DATA + "Customers", deletedCustomer));
    }
}
