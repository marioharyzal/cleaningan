package com.cleaningan.service;

import com.cleaningan.constant.MessageException;
import com.cleaningan.constant.WashStatus;
import com.cleaningan.entity.*;
import com.cleaningan.exception.NotFoundException;
import com.cleaningan.model.request.DetailTransactionRequest;
import com.cleaningan.model.request.TransactionRequest;
import com.cleaningan.repository.*;
import com.cleaningan.util.GenerateDate;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionRepository transactionRepository;

    private final IDetailTransactionRepository detailTransactionRepository;

    private final IPacketTypeRepository packetTypeRepository;

    private final ICustomerRepository customerRepository;

    private final IShoeTypeRepository shoeTypeRepository;

    private final ModelMapper modelMapper;

    public TransactionServiceImpl(ITransactionRepository transactionRepository, IDetailTransactionRepository detailTransactionRepository, IPacketTypeRepository packetTypeRepository, ICustomerRepository customerRepository, IShoeTypeRepository shoeTypeRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.detailTransactionRepository = detailTransactionRepository;
        this.packetTypeRepository = packetTypeRepository;
        this.customerRepository = customerRepository;
        this.shoeTypeRepository = shoeTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Transaction saveTransaction(TransactionRequest transactionRequest) {
        System.out.println("=====" + transactionRequest);
        try {
            Date entryDate = GenerateDate.generate(transactionRequest.getEntryDate());

            //query packet type
            Optional<PacketType> packetTypeId = packetTypeRepository.findByPacketTypeId(transactionRequest.getPacketType());
            if (packetTypeId.isEmpty())
                throw new NotFoundException("Packet type " + MessageException.NOT_FOUND);

            Optional<Customer> customerByEmail = customerRepository.findByCustomerEmail(transactionRequest.getCustomerEmail());
            if (customerByEmail.isEmpty())
                throw new NotFoundException("Customer " + MessageException.NOT_FOUND);

            //calculate out date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(transactionRequest.getEntryDate()));
            c.add(Calendar.DATE, packetTypeId.get().getWashingTime());
            String outDateString = sdf.format(c.getTime());

            Transaction transactionMap = modelMapper.map(transactionRequest, Transaction.class);

            transactionMap.setCustomer(customerByEmail.get());
            transactionMap.setPacketTypeId(packetTypeId.get());
            transactionMap.setEntryDate(entryDate);
            transactionMap.setOutDate(GenerateDate.generate(outDateString));
            transactionMap.setWashStatus(WashStatus.NOT_DONE.toString());

            System.out.println("===transaction map=== " + transactionMap);
            Transaction transaction = transactionRepository.save(transactionMap);
            System.out.println("===transaction save=== " + transaction);
            AtomicInteger shoeTypeId = new AtomicInteger();
            List<DetailTransaction> detailTransaction = transactionMap.getDetailTransaction().stream().map(tx -> {
                List<Integer> shoeTypeIds = transactionRequest.getDetailTransactions().stream().map(DetailTransactionRequest::getShoeTypeId
                ).collect(Collectors.toList());

                shoeTypeIds.forEach(id -> {
                    if (tx.getShoeType().getShoeTypeId().equals(id)) {
                        shoeTypeId.set(id);
                    }
                });
                Optional<ShoeType> shoeTypeById = shoeTypeRepository.findByShoeTypeId(shoeTypeId.get());

                tx.setTransaction(transaction);
                tx.setShoeType(shoeTypeById.get());

                return tx;
            }).collect(Collectors.toList());
            System.out.println("===detail ini ga dapet:=== " + detailTransaction);
            detailTransactionRepository.saveAll(detailTransaction);
            return null;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
