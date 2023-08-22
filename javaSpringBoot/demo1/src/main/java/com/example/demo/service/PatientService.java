package com.example.demo.service;

import com.example.demo.entity.Patient;

public interface PatientService {
    Patient getPatientById(Integer patientId);

    Patient getPatientByEmail(String email);
}
