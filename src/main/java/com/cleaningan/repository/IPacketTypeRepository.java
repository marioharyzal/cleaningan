package com.cleaningan.repository;

import com.cleaningan.entity.PacketType;
import com.cleaningan.entity.ShoeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IPacketTypeRepository extends JpaRepository<PacketType, String> {

    @Query(value = "select * from packet_type", nativeQuery = true)
    List<PacketType> findAllPacketType();

    @Query(value = "select * from packet_type p where p.packet_type_id = ?1", nativeQuery = true)
    Optional<PacketType> findByPacketTypeId(Integer packetTypeId);

    @Query(value = "select (count(p) > 0) from packet_type p where p.packet_type_id = ?1", nativeQuery = true)
    boolean existsByPacketTypeId(Integer packetTypeId);

    @Transactional
    @Modifying
    @Query(value = "delete from packet_type p where p.packet_type_id = ?1", nativeQuery = true)
    int deleteByPacketTypeId(Integer packetTypeId);

}