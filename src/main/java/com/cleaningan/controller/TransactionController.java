package com.cleaningan.controller;

import com.cleaningan.constant.MessageResponse;
import com.cleaningan.entity.Transaction;
import com.cleaningan.model.request.TransactionRequest;
import com.cleaningan.model.response.SuccessResponse;
import com.cleaningan.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
@Validated
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity addTransaction(@Valid @RequestBody TransactionRequest transactionRequest) throws Exception {

        Transaction transaction = transactionService.saveTransaction(transactionRequest);

        System.out.println();
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(MessageResponse.SUCCESS_CREATE + "Transaction!", transaction));
    }

}
