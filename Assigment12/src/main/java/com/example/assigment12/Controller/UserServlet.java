package com.example.assigment12.Controller;

import com.example.assigment12.Dao.UserDao;
import com.example.assigment12.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", value = "/userServlet")
public class UserServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String updateId = req.getParameter("update");
        String deleteId = req.getParameter("delete");
        String userId = req.getParameter("userId");
        String userIdUpdate = req.getParameter("id");
        String userName = req.getParameter("name");
        String email = req.getParameter("email");
        String createOrUpdate = req.getParameter("createOrUpdate");
        if(createOrUpdate!=null){
            if(!userIdUpdate.isEmpty()){
                UserEntity user = userDao.getUserById(Integer.parseInt(userIdUpdate.trim()));
                user.setEmail(email);
                user.setName(userName);
                userDao.updateUser(Integer.parseInt(userIdUpdate.trim()),user);
                List<UserEntity> users = userDao.getAllUser();
                req.setAttribute("users", users);
                req.getRequestDispatcher("/user/userList.jsp").forward(req, resp);
            }else {
                UserEntity user = new UserEntity();
                user.setName(userName);
                user.setEmail(email);
                userDao.saveUser(user);
                resp.sendRedirect("hello-servlet");
            }
        }else {
            if (updateId != null) {
                UserEntity user = userDao.getUserById(Integer.parseInt(userId.trim()));
                req.setAttribute("user",user);
                req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
            } else if (deleteId != null) {
                userDao.deleteUserById(Integer.parseInt(userId));
                resp.sendRedirect("hello-servlet");
            }
        }


    }
}
