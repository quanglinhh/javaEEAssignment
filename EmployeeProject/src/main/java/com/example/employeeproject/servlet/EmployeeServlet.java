package com.example.employeeproject.servlet;

import com.example.employeeproject.Dao.EmployeeDao;
import com.example.employeeproject.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "employeeServlet", value = "/employeeServlet")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao;

    public void init() {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String position = req.getParameter("position");
        String department = req.getParameter("department");
        String birthDay = req.getParameter("birthDay");
        String address = req.getParameter("address");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee employee = new Employee();
        employee.setBirthDay(date);
        employee.setAddress(address);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setFullName(fullName);
        employeeDao.saveEmployee(employee);
        resp.sendRedirect("/hello-servlet");
    }
}
