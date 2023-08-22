package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("api/patients")

public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/email")
    public Patient getPatientByEmail(@RequestParam(name = "email") String email){
        return patientService.getPatientByEmail(email);
    }
    @GetMapping("/id")
    public Patient getPatientById(@RequestParam(name = "id") Integer patientId){
        return patientService.getPatientById(patientId);
    }
}
