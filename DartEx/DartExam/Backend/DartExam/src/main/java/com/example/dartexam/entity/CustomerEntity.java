package com.example.dartexam.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "CUSTOMER")
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    private Date dateOfBirth;

    private String fullName;

    private String address;

    private String phoneNumber;


}
