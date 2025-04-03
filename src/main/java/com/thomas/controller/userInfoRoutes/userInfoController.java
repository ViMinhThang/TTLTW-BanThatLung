package com.thomas.controller.userInfoRoutes;

import com.thomas.dao.model.User;
import com.thomas.services.MD5Service;
import com.thomas.services.UploadUserService;
import com.thomas.services.EmailService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "userInfoController", value = "/userInfo")
public class userInfoController extends HttpServlet {
    UploadUserService uploadUserService = new UploadUserService();
    EmailService emailService = new EmailService();

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
                handlePasswordUpdate(request, response, userId);
                response.sendRedirect("/userInfo");
            } else {
                request.setAttribute("errorMessage", "Sai mật khẩu!");
                request.getRequestDispatcher("/userInfo").forward(request, response);
            }
        } else if (message.equals("updateEmail")) {
            String password = request.getParameter("password");
            String email = request.getParameter("newEmail");
            if (uploadUserService.updateEmail(request, email, userId, password)) {
                handleEmailUpdate(request, response, userId);
                response.sendRedirect("/userInfo");
            } else {
                request.setAttribute("missmatchPassword", "Sai mật khẩu!");
                request.getRequestDispatcher("/userInfo").forward(request, response);
            }
        }
    }

    private void handleEmailUpdate(HttpServletRequest request, HttpServletResponse response, int userId) throws IOException, ServletException {
        String newEmail = request.getParameter("newEmail");
        String password = request.getParameter("password");

        // cập nhật trong database
        boolean success = uploadUserService.updateEmail(request, newEmail, userId, password);
        if (success) {
            // Gửi mail thông báo
            String subject = "Thông báo thay đổi email";
            String content = "Email của bạn đã được thay đổi thành công sang: " + newEmail;
            emailService.sendEmail(newEmail, subject, content);
        } else {
            request.setAttribute("errorMessage", "Cập nhật mật khẩu không thành công!");
            request.getRequestDispatcher("/userInfo").forward(request, response);
        }
    }

    private void handlePasswordUpdate(HttpServletRequest request, HttpServletResponse response, int userId) throws IOException, ServletException {
        User user = uploadUserService.getUser(userId);
        String newPassword = request.getParameter("updatePassword");

        // cập nhật database
        boolean success = uploadUserService.updateUserPassword(newPassword, userId);
        String userEmail = user.getEmail();
        if (success) {

            // gửi thông báo
            String subject = "Thông báo thay đổi mật khẩu";
            String content = "Mật khẩu của bạn đã được thay đổi thành công.";
            emailService.sendEmail(userEmail, subject, content);
        } else {
            request.setAttribute("errorMessage", "Cập nhật mật khẩu không thành công!");
            request.getRequestDispatcher("/userInfo").forward(request, response);
        }
    }
}

