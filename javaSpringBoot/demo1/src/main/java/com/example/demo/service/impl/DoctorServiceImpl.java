package com.example.demo.service.impl;

import com.example.demo.entity.Doctor;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor getDocTorById(Integer doctorId) {
        try {
            Doctor doctor = doctorRepository.findById(doctorId).get();
            if(doctor != null){
                return doctor;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Doctor getDoctorByUserId(Integer userId) {
        try{
            Doctor doctor = doctorRepository.getDoctorByUserId(userId);
            return doctor;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
