package com.cleaningan.service;

import com.cleaningan.constant.MessageException;
import com.cleaningan.entity.Membership;
import com.cleaningan.repository.IMembershipRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements IMembershipService {

    private final IMembershipRepository membershipRepository;

    public MembershipServiceImpl(IMembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Membership> findAll() {
        try {
            return membershipRepository.findAllMembership();
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public Membership save(Membership membership) {
        try {
            boolean existMembership = membershipRepository.existsByMembershipId(membership.getMembershipId());
            if (existMembership) throw new DataIntegrityViolationException(MessageException.DATA_EXIST.toString());
            return membershipRepository.save(membership);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public Membership findOne(int id) {
        try {
            Optional<Membership> membershipById = membershipRepository.findByMembershipId(id);
            if (membershipById.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            return membershipById.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public Membership update(int id, Membership membership) {
        try {
            boolean existsMembershipId = membershipRepository.existsByMembershipId(id);
            if (!existsMembershipId) throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            membership.setMembershipId(id);
            return membershipRepository.save(membership);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public Membership delete(int id) {
        try {
            Optional<Membership> membershipById = membershipRepository.findByMembershipId(id);
            if (membershipById.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            membershipRepository.deleteByMembershipId(id);
            return membershipById.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }


    @Override
    public Membership findOne(String id) {
        return null;
    }

    @Override
    public Membership update(String id, Membership membership) {
        return null;
    }

    @Override
    public Membership delete(String id) {
        return null;
    }
}
