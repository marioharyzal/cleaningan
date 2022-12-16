package com.cleaningan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packet_type")
@Getter
@Setter
@ToString
public class PacketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "packet_type_id")
    private Integer packetTypeId;

    @Column(name = "packet_name", nullable = false, length = 100)
    private String packetName;

    @Column(name = "packet_description", nullable = false)
    private String packetDescription;

    @Column(name = "washing_time", nullable = false)
    private Integer washingTime;

    @OneToMany(mappedBy = "packetTypeId", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Transaction> transactions;
}
