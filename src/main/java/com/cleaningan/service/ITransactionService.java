package com.cleaningan.service;

import com.cleaningan.entity.DetailTransaction;
import com.cleaningan.entity.Transaction;
import com.cleaningan.model.request.DetailTransactionRequest;
import com.cleaningan.model.request.TransactionRequest;

public interface ITransactionService {

    Transaction saveTransaction(TransactionRequest transactionRequest);

}
