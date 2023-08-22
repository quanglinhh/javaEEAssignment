package com.example.demo.service.impl;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient getPatientById(Integer patientId){
        try {
            Patient patient = patientRepository.findById(patientId).get();
            if(patient != null){
                return patient;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient getPatientByEmail(String email) {
        try {
            List<Patient> patients = patientRepository.getByEmail(email);
            if(patients.size()==1){
                return patients.get(0);
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
