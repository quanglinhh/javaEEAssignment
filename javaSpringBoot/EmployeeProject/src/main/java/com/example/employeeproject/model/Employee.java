package com.example.employeeproject.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    private String employeeName;

    private Integer age;

    private String address;

    private String email;
}
