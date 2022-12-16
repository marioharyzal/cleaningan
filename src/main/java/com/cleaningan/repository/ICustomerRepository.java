package com.cleaningan.repository;

import com.cleaningan.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "select * from customer", nativeQuery = true)
    List<Customer> findAllCustomer();

    @Query(value = "select (count(c) > 0) from customer c where c.customer_email = ?1", nativeQuery = true)
    boolean existsByCustomerEmail(String customerEmail);

    @Query(value = "select * from customer where customer_email = ?1", nativeQuery = true)
    Optional<Customer> findByCustomerEmail(String customerEmail);

    @Transactional
    @Modifying
    @Query(value = "delete from customer where customer_email = ?1", nativeQuery = true)
    void deleteByCustomerEmail(String customerEmail);


}