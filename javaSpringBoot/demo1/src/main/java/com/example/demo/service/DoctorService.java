package com.example.demo.service;

import com.example.demo.entity.Doctor;

public interface DoctorService {
    Doctor getDocTorById(Integer doctorId);

    Doctor getDoctorByUserId(Integer userId);
}
