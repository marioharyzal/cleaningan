package com.cleaningan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "detail_transaction")
@Getter
@Setter
@ToString
public class DetailTransaction {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "detail_transaction_id", unique = true,nullable = false)
    private String detailTransactionId;

    @Column(name = "shoe_units", nullable = false)
    private Integer shoeUnits;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "shoe_type_id", nullable = false)
    private ShoeType shoeType;

}
