package com.cleaningan.service;

import com.cleaningan.constant.MessageException;
import com.cleaningan.entity.Customer;
import com.cleaningan.entity.Membership;
import com.cleaningan.exception.NotFoundException;
import com.cleaningan.repository.ICustomerRepository;
import com.cleaningan.repository.IMembershipRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final IMembershipRepository membershipRepository;

    public CustomerServiceImpl(ICustomerRepository customerRepository, IMembershipRepository membershipRepository) {
        this.customerRepository = customerRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Customer> findAll() {
        try {
            return customerRepository.findAllCustomer();
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        try {
            boolean existCustomer = customerRepository.existsByCustomerEmail(customer.getCustomerEmail());
            if (existCustomer) throw new DataIntegrityViolationException(MessageException.DATA_EXIST.toString());

            Optional<Membership> existMembershipId = membershipRepository.findByMembershipId(customer.getMembership().getMembershipId());

            if (existMembershipId.isEmpty()) throw new NotFoundException("Membership " + MessageException.NOT_FOUND);

            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public Customer findOne(String email) {
        try {
            Optional<Customer> customerByEmail = customerRepository.findByCustomerEmail(email);
            if (customerByEmail.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            return customerByEmail.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public Customer update(String email, Customer customer) {
        try {
            boolean existsMembership = customerRepository.existsByCustomerEmail(email);
            if (!existsMembership) throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());

            Optional<Membership> existMembershipId = membershipRepository.findByMembershipId(customer.getMembership().getMembershipId());

            if (existMembershipId.isEmpty()) throw new NotFoundException("Membership " + MessageException.NOT_FOUND);

            customer.setCustomerEmail(email);
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public Customer delete(String email) {
        try {
            Optional<Customer> membershipByEmail = customerRepository.findByCustomerEmail(email);
            if (membershipByEmail.isEmpty())
                throw new DataIntegrityViolationException(MessageException.NOT_FOUND.toString());
            customerRepository.deleteByCustomerEmail(email);
            return membershipByEmail.get();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            throw new RuntimeException(MessageException.INTERNAL_SERVER_ERROR.toString());
        }
    }
}
