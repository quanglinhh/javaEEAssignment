package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Specialization {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    private String specName;

    @OneToOne(mappedBy = "specialization", cascade = CascadeType.ALL)
    @JsonBackReference
    private Doctor doctor;


}
