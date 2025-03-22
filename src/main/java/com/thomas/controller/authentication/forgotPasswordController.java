package com.thomas.controller.authentication;

import com.thomas.dao.model.Token;
import com.thomas.dao.model.User;
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
        String host = "smtp.gmail.com";
        String port = "587";
        String from = "huynhminhthang246@gmail.com";
        String password = "zhfz gtpx bkbp cuuo";
        //Properties : Khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP HOST
        props.put("mail.smtp.port", port); //TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        //Phiên làm việc
        Session session = Session.getInstance(props, auth);
        //Gửi email
        String to = "huynhminhthang246@gmail.com";

        //Tạo một tin nhắn mới
        MimeMessage message = new MimeMessage(session);
        try {
            String token = UUID.randomUUID().toString();
            String resetLink = "http://localhost:8080/resetPassword?token=" + token;
            String messageContent = "Bạn đã yêu cầu đặt lại mật khẩu.\n" +
                    "Vui lòng nhấn vào liên kết sau để đặt lại mật khẩu:\n" +
                    resetLink + "\n\n" +
                    "Nếu bạn không yêu cầu điều này, vui lòng bỏ qua email này.";

            //Kiểu nội dung
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            //Người gửi
            message.setFrom(from);
            //Ngời nhận
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(toEmail, false));
            // Tiêu đề email
            message.setSubject("Đặt lại mật khẩu");
            //Nội dung
            message.setText(messageContent, "UTF-8");
            // Gủi email
            Transport.send(message);
            User u = uploadUserService.findUserByEmail(toEmail);
            Token userToken = new Token(token, u.getId(), LocalDateTime.now().plusHours(24));
            tokenService.saveResetToken(userToken);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/");
    }
}

