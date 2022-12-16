package com.cleaningan.repository;

import com.cleaningan.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IMembershipRepository extends JpaRepository<Membership, Integer> {

    @Query(value = "select * from membership", nativeQuery = true)
    List<Membership> findAllMembership();

    @Transactional
    @Query(value = "select * from membership m where m.membership_id = ?1", nativeQuery = true)
    Optional<Membership> findByMembershipId(Integer membershipId);

    @Query(value = "select (count(m) > 0) from membership m where m.membership_id = ?1", nativeQuery = true)
    boolean existsByMembershipId(Integer membershipId);

    @Transactional
    @Modifying
    @Query(value = "delete from membership m where m.membership_id = ?1", nativeQuery = true)
    void deleteByMembershipId(Integer membershipId);
}