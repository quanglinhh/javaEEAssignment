package com.example.demo.service.impl;

import com.example.demo.entity.Patient;
import com.example.demo.model.MedicationDetail;
import com.example.demo.model.TestResult;
import com.example.demo.model.request.CreateMedicalRecord;
import com.example.demo.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class PdfService {
    @Autowired
    PatientService patientService;
    public String generatePdf(CreateMedicalRecord createMedicalRecord, String imageString) throws IOException, DocumentException {
        Patient patient = patientService.getPatientById(createMedicalRecord.getPatientId());
        String outputFile = "src/main/resources/medical_record.pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        document.open();

        BaseFont unicodeFont = BaseFont.createFont("src/main/resources/font/NotoSans-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        // Add content to the PDF
        Font titleFont = new Font(unicodeFont, 18, Font.BOLD);
        Font normalFont = new Font(unicodeFont, 12);
        Font boldFont = new Font(unicodeFont, 12, Font.BOLD);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingAfter(10f);

        Paragraph title = new Paragraph("MEDICAL REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph date = new Paragraph("Date: 8/2/2023\n(DD/MM/YYYY)", normalFont);
        date.setAlignment(Element.ALIGN_CENTER);
        document.add(date);

        document.add(Chunk.NEWLINE);

        PdfPTable nameTable = createTable(normalFont, "1. Name:",patient.getFullName(),boldFont);
        document.add(nameTable);

        PdfPTable addressTable = createTable(normalFont, "2. Address:",patient.getAddress(),boldFont);
        document.add(addressTable);

        PdfPTable phoneTable = createTable(normalFont, "3. Phone number:",patient.getPhoneNumber(),boldFont);
        document.add(phoneTable);

        PdfPTable birthDayTable = createTable(normalFont, "4. Birthday:",patient.getDateOfBirth().toString(),boldFont);
        document.add(birthDayTable);

        document.add(new Paragraph("5. Sex: " + getCheckmarkSymbol() + " Male   " + getCheckmarkSymbol() + " Female", boldFont));

        document.add(new Paragraph("5.5. Images:", boldFont));
        //String images = "[\"images/bi-quyet-de-chup-buc-anh-dep-tu-nhien_photozone-com-vn-1.jpg\",\"images/Dzung_Nguyen_.jpg\"]";
        PdfPTable imageTable = imageTable(imageString);
        document.add(imageTable);


        String testResultJson = createMedicalRecord.getTestResult();
        document.add(new Paragraph("6. Test results:", boldFont));
        PdfPTable testResultsTable = createTestResultsTable(normalFont, testResultJson,boldFont);
        document.add(testResultsTable);

        String medicalDetailsString = createMedicalRecord.getMedicationDetails();
        document.add(new Paragraph("100. Medical Details:", boldFont));
        PdfPTable medicalDetailsTable = createMedicalDetailsTable(normalFont, medicalDetailsString);
        document.add(medicalDetailsTable);


        document.add(new Paragraph("7. Current condition:", boldFont));
        PdfPTable currentConditionTable = createCommonListTable(normalFont, createMedicalRecord.getCurrentCondition());
        document.add(currentConditionTable);


        document.add(new Paragraph("8. Disease progression:", boldFont));
        PdfPTable diseaseProgressionTable = createCommonListTable(normalFont, createMedicalRecord.getDiseaseProgression());
        document.add(diseaseProgressionTable );

        PdfPTable doctorNameTable = createTable(normalFont, "9. Name of Doctor:",createMedicalRecord.getDoctorName(),boldFont);
        document.add(doctorNameTable);

        document.add(new Paragraph("10. Notes from doctor:", boldFont));
        document.add(new Paragraph(createMedicalRecord.getNoteFromDoctor(), normalFont));

        document.close();

        System.out.println("PDF created successfully!");
        return outputFile;
    }


    private static String getCheckmarkSymbol() {
        return String.valueOf((char) 0x2713);
    }

    private static void addCell2Collumn(PdfPTable table, String label, String value, Font font, Font labelFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, font));
        valueCell.setBorder(Rectangle.NO_BORDER);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }
    private static void addCell(PdfPTable table, String value, Font font) {
        PdfPCell valueCell = new PdfPCell(new Phrase(value, font));
        valueCell.setPadding(5);
        valueCell.setBorder(Rectangle.NO_BORDER);

        table.addCell(valueCell);
    }
    private static PdfPTable createTable(Font font, String label, String value, Font labelFont)throws JsonProcessingException{
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingAfter(10f);
        table.setSpacingBefore(5f);

        addCell2Collumn(table, label, value, font, labelFont);
        return table;
    }

    private static PdfPTable imageTable(String listImageUrls) throws IOException, BadElementException {
        String images = listImageUrls;
                //.replace("[", "").replace("]", "").replace("\"","");
        String[] imageArray = images.split(",");

       ArrayList<String> imageUrlList = new ArrayList<>(Arrays.asList(imageArray));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        for(String imageUrl : imageUrlList){
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL linkImage = classLoader.getResource(imageUrl);
            System.out.println(imageUrl);
            if (imageUrl != null) {
                if(linkImage != null){
                    //Image image = Image.getInstance(new URL(imageUrl));
                    Image image = Image.getInstance(linkImage);
                    PdfPCell cell = new PdfPCell(image, true);
                    cell.setPadding(10);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
            }

        }
       return table;
    }
    private static PdfPTable createCommonListTable(Font font, String jsonString) throws JsonProcessingException{
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingAfter(10f);

        String[] elements = jsonString.split("\\n");

        List<String> resultList = new ArrayList<>();

        for (String element : elements) {
            resultList.add(element.trim());
        }

        for (String result : resultList) {
            addCell(table, result, font);
        }
        return table;
    }
    private static PdfPTable createMedicalDetailsTable(Font font, String mediationDetailsString) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<MedicationDetail> medicationDetailList = objectMapper.readValue(mediationDetailsString, new TypeReference<List<MedicationDetail>>() {
        });

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingAfter(10f);


        for (MedicationDetail detail : medicationDetailList) {
            addCell2Collumn(table, "Medicine Name:", detail.getMedicineName(), font, font);
            addCell2Collumn(table, "Frequency:", detail.getFrequency(), font, font);
            addCell2Collumn(table, "Duration:", detail.getDuration(), font, font);
            addSeparator(table);
        }
        return table;
    }

    private static void addSeparator(PdfPTable table) {
        PdfPCell separatorCell = new PdfPCell();
        separatorCell.setBorder(Rectangle.BOTTOM);
        separatorCell.setColspan(2);
        separatorCell.setFixedHeight(10f);
        table.addCell(separatorCell);
    }

    private static PdfPTable createTestResultsTable(Font font, String testResultJson, Font boldFont) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        TestResult testResult = objectMapper.readValue(testResultJson, TestResult.class);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingAfter(10f);
        table.setSpacingBefore(10f);


        List<Map<String, List<String>>> datas = testResult.getData();

        for (Map<String, List<String>> map : datas) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                PdfPCell cell = new PdfPCell(new Phrase(key, boldFont));
                cell.setPadding(5);
                cell.setBorder(Rectangle.NO_BORDER);

                table.addCell(cell);

                for (String value : values) {
                    Paragraph testResultsParagraph = new Paragraph(value, font);
                    testResultsParagraph.setIndentationLeft(20f);
                    addCell(table,value,font);
                }
            }
            addSeparator(table);

        }

        return table;
    }



//    public static void main(String[] args) throws DocumentException, IOException {
//        Patient patient = new Patient();
//        patient.setAge(25);
//        patient.setEmail("linhdqth2108046@fpt.com.vn");
//        patient.setFullName("Đào Quang Linh");
//        patient.setGeder("Male");
//        patient.setAddress("123 Đường ABC, Quận XYZ, Thành phố HCM");
//        patient.setPhoneNumber("0901 234 567");
//        patient.setDateOfBirth("15/05/1980");
//        String jsonString = "{\"data\":[{\"Xét nghiệm hóa sinh\":[\"Máu: Glucose: 90 mg/dL, Cholesterol: 180 mg/dL\",\"Nước tiểu: Glucose: âm tính, Protein: âm tính\"]},{\"Chẩn đoán hình ảnh:\":[\"X-quang phổi: Không có bất thường\",\"Siêu âm bụng: Thận trái bình thường, gan không có vấn đề gì đáng ngại\"]}]}";
//        String mediationDetailsString = "[{\"medicineName\":\"me1\",\"frequency\":\"fre1\",\"duration\":\"dura1\"},{\"medicineName\":\"me1\",\"frequency\":\"fre1\",\"duration\":\"dura1\"}]";
//        String currentConditonString = "- Triệu chứng: Ho nhẹ, sốt nhẹ\n- Dấu hiệu: Hơi mệt mỏi sau khi ho\n- Tình trạng sức khỏe: Khá";
//        String diseaseProgression = "-Tình hình tiến triển bệnh: Ho đã giảm sau khi dùng thuốc\n- Phản ứng với điều trị: Tốt\n- không có phản ứng phụ\n- Sự kiện quan trọng: Không có";
//        CreateMedicalRecord createMedicalRecord = new CreateMedicalRecord();
//        createMedicalRecord.setPatient(patient);
//        createMedicalRecord.setDoctorName("Trần Văn A");
//        createMedicalRecord.setNoteFromDoctor("Bệnh nhân cần tiếp tục sử dụng thuốc theo đúng hướng dẫn và tái khám sau 1 tuần");
//        createMedicalRecord.setTestResult(jsonString);
//        createMedicalRecord.setCurrentCondition(currentConditonString);
//        createMedicalRecord.setDiseaseProgression(diseaseProgression);
//        createMedicalRecord.setMedicationDetails(mediationDetailsString);
//        generatePdf(createMedicalRecord);
//
//    }
}
