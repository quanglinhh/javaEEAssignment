package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Patient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    private String fullName;
    private int age;
    private String email;
    private String geder;

    private String image;

    private String height;

    private String weight;

    private String address;

    private String phoneNumber;

    private Date dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserDB user;

    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Appointment> appointments;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", geder='" + geder + '\'' +
                ", image='" + image + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
