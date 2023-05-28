package com.example.assigment12;

import antlr.StringUtils;
import com.example.assigment12.Dao.UserDao;
import com.example.assigment12.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userServlet", value = "/userServlet")
public class UserServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String updateId = req.getParameter("update");
        String deleteId = req.getParameter("delete");
        String userId = req.getParameter("userId");

        if (updateId != null) {
            UserEntity user = userDao.getUserById(Integer.parseInt(userId));
            req.setAttribute("user",user);
            req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
        } else if (deleteId != null) {
            userDao.deleteUserById(Integer.parseInt(userId));
            resp.sendRedirect("hello-servlet");
        }

        // Tiếp tục xử lý và đưa ra phản hồi
        // ...
    }
}
