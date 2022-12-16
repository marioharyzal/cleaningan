package com.cleaningan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shoe_type")
@Getter
@Setter
@ToString
public class ShoeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoe_type_id")
    private Integer shoeTypeId;

    @Column(name = "type_name", nullable = false, length = 200)
    private String typeName;

    @Column(name = "type_description", nullable = false)
    private String typeDescription;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @OneToMany(mappedBy = "shoeType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DetailTransaction> detailTransactionList;
}
