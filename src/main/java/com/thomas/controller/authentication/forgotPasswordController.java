package com.thomas.controller.authentication;

import com.thomas.dao.model.Token;
import com.thomas.dao.model.User;
import com.thomas.services.EmailService;
import com.thomas.services.TokenService;
import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

@WebServlet(name = "forgotPasswordController", value = "/forgotPassword")
public class forgotPasswordController extends HttpServlet {
    UploadUserService uploadUserService = new UploadUserService();
    TokenService tokenService = new TokenService();
    EmailService emailService = new EmailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("auth") == null) {
            request.getRequestDispatcher("/frontend/signInPage/forgetPasswordPage/forgetPasswordPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toEmail = request.getParameter("email");

        String subject = "Đặt lại mật khẩu";

        String token = UUID.randomUUID().toString();
        String resetLink = "http://localhost:8080/resetPassword?token=" + token;
        String messageContent = "Bạn đã yêu cầu đặt lại mật khẩu.\n" +
                "Vui lòng nhấn vào liên kết sau để đặt lại mật khẩu:\n" +
                resetLink + "\n\n" +
                "Nếu bạn không yêu cầu điều này, vui lòng bỏ qua email này.";

        emailService.sendEmail(toEmail, subject, messageContent);

        User u = uploadUserService.findUserByEmail(toEmail);
        Token userToken = new Token(token, u.getId(), LocalDateTime.now().plusHours(24));
        tokenService.saveResetToken(userToken);
        response.sendRedirect("/");
    }
}

