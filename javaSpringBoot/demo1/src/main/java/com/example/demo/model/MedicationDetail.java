package com.example.demo.model;

import com.example.demo.entity.MedicalRecord;
import lombok.Data;


@Data
public class MedicationDetail {
    private String medicineName;

    private String frequency;

    private String duration;

}
