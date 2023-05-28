package com.example.assigment12;

import com.example.assigment12.Dao.UserDao;
import com.example.assigment12.model.UserEntity;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private UserDao userDao;

    public void init() {

        message = "Hello World!";
        userDao = new UserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        List<UserEntity> users = userDao.getAllUser();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/user/userList.jsp").forward(request, response);
    }

    public void destroy() {
    }
}