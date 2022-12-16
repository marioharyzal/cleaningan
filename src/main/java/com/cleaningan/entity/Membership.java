package com.cleaningan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "membership")
@Getter
@Setter
@ToString
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_id")
    private Integer membershipId;

    @Column(name = "membership_name", nullable = false, length = 100)
    private String membershipName;

    @Column(name = "membership_description", nullable = false)
    private String membershipDescription;

    @Column(name = "discount_amount", nullable = false, length = 3)
    private Integer discountAmount;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Customer> customers;
}
