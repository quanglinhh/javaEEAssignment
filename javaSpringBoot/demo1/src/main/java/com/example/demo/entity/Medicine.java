package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Medicine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    private String name;

    private String note;


//
//    @OneToMany(mappedBy = "medicine",cascade = CascadeType.ALL)
//    private List<MedicationDetail> medicationDetails;

    @CreationTimestamp
    private Date createdAt;
}
