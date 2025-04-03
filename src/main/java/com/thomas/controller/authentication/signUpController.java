package com.thomas.controller.authentication;

import com.thomas.dao.model.Token;
import com.thomas.dao.model.User;
import com.thomas.services.EmailService;
import com.thomas.services.SignUpService;
import com.thomas.services.TokenService;
import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.UUID;

@WebServlet(name = "signUpController", value = "/signup")
public class signUpController extends HttpServlet {
    TokenService service = new TokenService();
    SignUpService signUpService = new SignUpService();
    UploadUserService uploadUserService = new UploadUserService();
    EmailService emailService = new EmailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("auth") == null) {
            request.getRequestDispatcher("/frontend/signInPage/signUpPage/signUp.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = null;
        String toEmail = request.getParameter("email");

        token = UUID.randomUUID().toString();
        String subject = "Xác nhận tài khoản qua email";

        String resetLink = "http://localhost:8080/verify?token=" + token;
        String messageContent = "Vui lòng nhấn vào đường dẫn này để kích hoạt tài khoản.\n" +
                resetLink + "\n" +
                "Nếu bạn không yêu cầu điều này, vui lòng bỏ qua email này.";

        emailService.sendEmail(toEmail, subject, messageContent);

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String middleName = request.getParameter("middleName");
        String birthDateString = request.getParameter("birthDate");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        boolean isSuccess = signUpService.signUp(email, password, name, middleName, birthDate);
        User u = uploadUserService.findUserByEmail(email);
        Token userToken = new Token(token, u.getId(), LocalDateTime.now().plusHours(24));
        boolean isSaveToken = service.saveResetToken(userToken);

        if (!isSuccess || !isSaveToken) {
            request.setAttribute("errorMessage", "Tài khoản đã tồn tại hoặc có lỗi xảy ra.");
            request.getRequestDispatcher("/frontend/signInPage/signUpPage/signUp.jsp").forward(request, response);
            return;
        }

        String message1 = "Kiểm tra email của bạn để kích hoạt tài khoản";
        String encodedMessage = URLEncoder.encode(message1, StandardCharsets.UTF_8);

        response.sendRedirect("/verify?messageRedirect=" + encodedMessage);
    }
}

