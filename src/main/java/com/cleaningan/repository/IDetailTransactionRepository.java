package com.cleaningan.repository;

import com.cleaningan.entity.DetailTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IDetailTransactionRepository extends JpaRepository<DetailTransaction, String> {
    @Query(value = "select * from detail_transaction d where d.detail_transaction_id = ?1", nativeQuery = true)
    DetailTransaction findByDetailTransactionId(String detailTransactionId);

    @Transactional
    @Modifying
    @Query(value = "delete from detail_transaction d where d.detail_transaction_id = ?1", nativeQuery = true)
    int deleteByDetailTransactionId(String detailTransactionId);


}