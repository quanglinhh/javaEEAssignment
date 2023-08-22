package com.example.demo.service;

import com.example.demo.entity.MedicalRecord;
import com.example.demo.model.request.CreateMedicalRecord;

public interface MedicalRecordService {
    MedicalRecord createOrUpdateMedicalRecord(Integer medicalRecordId,CreateMedicalRecord requestBody);
}
