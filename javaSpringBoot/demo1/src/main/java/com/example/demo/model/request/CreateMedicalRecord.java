package com.example.demo.model.request;


import com.example.demo.entity.Patient;
import lombok.Data;


@Data
public class CreateMedicalRecord {
    private Integer patientId;
    private Integer doctorId;
    private String doctorName;
    private String testResult;
    private String currentCondition;
    private String noteFromDoctor;
    private String diseaseProgression;
    private String medicationDetails;
}
