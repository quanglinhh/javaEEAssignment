package com.example.demo.controller;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.MedicalRecord;
import com.example.demo.entity.Patient;
import com.example.demo.model.MedicationDetail;
import com.example.demo.model.request.CreateMedicalRecord;
import com.example.demo.service.DoctorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.MedicalRecordService;

import com.example.demo.service.PatientService;
import com.example.demo.service.impl.PdfService;
import com.example.demo.util.FileStorageService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/medicalRecord")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MedicalRecordController {
    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    PdfService pdfService;

    @Autowired
    EmailService emailService;


    @Autowired
    DoctorService doctorService;

    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    PatientService patientService;


    @PostMapping("/create")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@ModelAttribute CreateMedicalRecord requestBody, @RequestParam("files") MultipartFile[] files) {
        MedicalRecord medicalRecord = new MedicalRecord();
        try {
             medicalRecord = medicalRecordService.createOrUpdateMedicalRecord(null, requestBody);
            fileStorageService.uploadImage(files);
            List<String> imageList = new ArrayList<>();
            for (MultipartFile file : files) {
                String imagePath ="images/"+ file.getOriginalFilename();
                imageList.add(imagePath);
            }
            String imagesString =String.join(",", imageList);
            genFilePDF(requestBody, imagesString);

            return ResponseEntity.ok(medicalRecord);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void genFilePDF(CreateMedicalRecord createMedicalRecord, String imagesString) throws DocumentException, IOException, MessagingException {
        //Patient patient = patientService.getPatientByEmail("linhdqth2108046@fpt.edu.vn");
        Patient patient = patientService.getPatientById(createMedicalRecord.getPatientId());
        Doctor doctor = doctorService.getDocTorById(createMedicalRecord.getDoctorId());
        createMedicalRecord.setDoctorName(doctor.getFullName());


        String jsonString = "{\"data\":[{\"Xét nghiệm hóa sinh\":[\"Máu: Glucose: 90 mg/dL, Cholesterol: 180 mg/dL\",\"Nước tiểu: Glucose: âm tính, Protein: âm tính\"]},{\"Chẩn đoán hình ảnh:\":[\"X-quang phổi: Không có bất thường\",\"Siêu âm bụng: Thận trái bình thường, gan không có vấn đề gì đáng ngại\"]}]}";
        String mediationDetailsString = "[{\"medicineName\":\"me1\",\"frequency\":\"fre1\",\"duration\":\"dura1\"},{\"medicineName\":\"me1\",\"frequency\":\"fre1\",\"duration\":\"dura1\"}]";
        String currentConditonString = "- Triệu chứng: Ho nhẹ, sốt nhẹ\n- Dấu hiệu: Hơi mệt mỏi sau khi ho\n- Tình trạng sức khỏe: Khá";
        String diseaseProgression = " ";

//        createMedicalRecord.setNoteFromDoctor("Bệnh nhân cần tiếp tục sử dụng thuốc theo đúng hướng dẫn và tái khám sau 1 tuần");
//        createMedicalRecord.setTestResult(jsonString);
//        createMedicalRecord.setCurrentCondition(currentConditonString);
//        createMedicalRecord.setDiseaseProgression(diseaseProgression);
//        createMedicalRecord.setMedicationDetails(mediationDetailsString);
        String to = patient.getEmail();
        String subject = "Kết quả xét nghiệm";
        String text = "Cho bố mày gửi";

        String outputFile = pdfService.generatePdf(createMedicalRecord, imagesString);
        String filePath = outputFile;

        emailService.sendEmail(to,subject,text,filePath);
    }

}
