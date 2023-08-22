package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class UserDB {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    private String userName;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Patient patient;

    @CreationTimestamp
    private Date createdAt;

}
