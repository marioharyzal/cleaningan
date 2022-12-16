package com.cleaningan.repository;

import com.cleaningan.entity.ShoeType;
import com.cleaningan.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "select * from transaction", nativeQuery = true)
    List<ShoeType> findAllTransaction();

    @Query(value = "select * from transaction t where t.transaction_id = ?1", nativeQuery = true)
    Transaction findByTransactionId(String transactionId);

    @Query(value = "select (count(t) > 0) from transaction t where t.transaction_id = ?1", nativeQuery = true)
    boolean existsByTransactionId(String transactionId);

    @Transactional
    @Modifying
    @Query(value = "delete from transaction t where t.transaction_id = ?1", nativeQuery = true)
    int deleteByTransactionId(String transactionId);

}