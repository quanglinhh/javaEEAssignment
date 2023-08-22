package com.example.demo.controller;

import com.example.demo.entity.Doctor;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/doctor")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/getDoctorByUserId/userId/{userId}")
    public Doctor getDoctorByUserId(@PathVariable("userId") Integer userId){
        try {
            return doctorService.getDoctorByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void genfilePDF(){

    }
}
