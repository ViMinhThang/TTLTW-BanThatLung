package com.thomas.controller.userInfoRoutes;

import com.thomas.services.MD5Service;
import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "userInfoController", value = "/userInfo")
public class userInfoController extends HttpServlet {
    UploadUserService uploadUserService = new UploadUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/frontend/userInfoPage/account/userinfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (message.equals("updatePassword")) {
            String password = request.getParameter("currentPassword");
            String newPassword = request.getParameter("updatePassword");
            if (uploadUserService.getUser(userId).getPassword().equals(MD5Service.hashPassword(password))) {
                uploadUserService.updateUserPassword(MD5Service.hashPassword(newPassword), userId);
                response.sendRedirect("/userInfo");
            } else {
                request.setAttribute("errorMessage", "Sai mật khẩu!");
                request.getRequestDispatcher("/userInfo").forward(request, response);
            }
        } else if (message.equals("updateEmail")) {
            String password = request.getParameter("password");
            String email = request.getParameter("newEmail");
            if (uploadUserService.updateEmail(request, email, userId, password)) {
                response.sendRedirect("/userInfo");
            } else {
                request.setAttribute("missmatchPassword", "Sai mật khẩu!");
                request.getRequestDispatcher("/userInfo").forward(request, response);
            }
        }
    }
}

