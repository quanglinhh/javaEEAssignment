package com.example.demo.service.impl;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.MedicalRecord;
import com.example.demo.entity.Patient;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.MedicalRecordRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.model.request.CreateMedicalRecord;
import com.example.demo.service.DoctorService;
import com.example.demo.service.MedicalRecordService;
import com.example.demo.service.PatientService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Override
    public MedicalRecord createOrUpdateMedicalRecord(Integer medicalRecordId, CreateMedicalRecord requestBody) {
        MedicalRecord medicalRecord = new MedicalRecord();
        Patient patient = patientService.getPatientById(requestBody.getPatientId());
        System.out.println(patient.getFullName());
        try {
            if(medicalRecordId != null){
                medicalRecord = medicalRecordRepository.findById(medicalRecordId).get();

                medicalRecord.setDiseaseProgression(medicalRecord.getDiseaseProgression()+","+requestBody.getDiseaseProgression());
            }else {
                medicalRecord.setDiseaseProgression(requestBody.getDiseaseProgression());
            }
            Doctor doctor = doctorService.getDocTorById(requestBody.getDoctorId());
            medicalRecord.setMedicationDetails(requestBody.getMedicationDetails());
            medicalRecord.setCurrentCondition(requestBody.getCurrentCondition());
            medicalRecord.setNoteFromDoctor(requestBody.getNoteFromDoctor());
            medicalRecord.setPatient(patient);
            medicalRecord.setDoctor(doctor);
            medicalRecord.setDiseaseProgression(requestBody.getDiseaseProgression());
            medicalRecord.setTestResult(requestBody.getTestResult());
            //System.out.println(new Gson().toJson(medicalRecord));
            medicalRecordRepository.save(medicalRecord);
        } catch (Exception ex) {
            return null;
        }
        return medicalRecord;
    }
}
