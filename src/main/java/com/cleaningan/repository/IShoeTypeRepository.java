package com.cleaningan.repository;

import com.cleaningan.entity.ShoeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IShoeTypeRepository extends JpaRepository<ShoeType, String> {

    @Query(value = "select * from shoe_type", nativeQuery = true)
    List<ShoeType> findAllShoeType();

    @Query(value = "select * from shoe_type s where s.shoe_type_id = ?1", nativeQuery = true)
    Optional<ShoeType> findByShoeTypeId(Integer shoeTypeId);

    @Query(value = "select (count(s) > 0) from shoe_type s where s.shoe_type_id = ?1", nativeQuery = true)
    boolean existsByShoeTypeId(Integer shoeTypeId);

    @Transactional
    @Modifying
    @Query(value = "delete from shoe_type s where s.shoe_type_id = ?1", nativeQuery = true)
    void deleteByShoeTypeId(Integer shoeTypeId);
}