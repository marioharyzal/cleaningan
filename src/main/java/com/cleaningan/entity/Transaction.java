package com.cleaningan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@ToString
public class Transaction {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;

    @Column(name = "entry_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @Column(name = "wash_status", nullable = false, length = 15)
    private String washStatus;

    @Column(name = "out_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date outDate;

    @ManyToOne
    @JoinColumn(name = "customer_email", nullable = false, referencedColumnName = "customer_email")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "packet_type_id", nullable = false)
    private PacketType packetTypeId;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<DetailTransaction> detailTransaction;
}
