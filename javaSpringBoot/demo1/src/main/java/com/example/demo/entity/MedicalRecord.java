package com.example.demo.entity;

import com.example.demo.model.MedicationDetail;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class MedicalRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String medicalHistory;

    private String images;

    private String testResult;

    private String currentCondition;

    private String noteFromDoctor;

    private String diseaseProgression;

    @CreationTimestamp
    private Date createdAt;

    private String medicationDetails;




}
