package com.example.employeeproject;

import com.example.employeeproject.Dao.EmployeeDao;
import com.example.employeeproject.model.Employee;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private EmployeeDao employeeDao;


    private String message;

    public void init() {
        message = "";
        employeeDao = new EmployeeDao();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        SimpleDateFormat dateFormatt = new SimpleDateFormat("dd-MM-yyyy");

        response.setContentType("text/html");
        List<Employee> employees = employeeDao.getAllEmployee();
        List<String> birthDays = new ArrayList<>();
        for(Employee employee: employees){
            String birth = dateFormatt.format(employee.getBirthDay());
            birthDays.add(birth);
        }
        request.setAttribute("employees",employees);
        request.setAttribute("birthDays",birthDays);
        request.getRequestDispatcher("/EmployeeList.jsp").forward(request, response);
    }

    public void destroy() {
    }
}