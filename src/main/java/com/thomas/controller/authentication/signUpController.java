package com.thomas.controller.authentication;

import com.thomas.dao.model.Token;
import com.thomas.dao.model.User;
import com.thomas.services.EmailService;
import com.thomas.services.SignUpService;
import com.thomas.services.TokenService;
import com.thomas.services.UploadUserService;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;
import org.json.JSONObject;

@WebServlet(name = "signUpController", value = "/signup")
public class signUpController extends HttpServlet {
    TokenService service = new TokenService();
    SignUpService signUpService = new SignUpService();
    UploadUserService uploadUserService = new UploadUserService();
    EmailService emailService = new EmailService();

    // Add your reCAPTCHA secret key
    private static final String SECRET_KEY = "6LftTQgrAAAAAO6Q7m6jONYGCmgKOgVLQWE7AAg-";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("auth") == null) {
            request.getRequestDispatcher("/frontend/signInPage/signUpPage/signUp.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verify reCAPTCHA first
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean isCaptchaValid = verifyCaptcha(gRecaptchaResponse);

        if (!isCaptchaValid) {
            request.setAttribute("errorMessage", "CAPTCHA không hợp lệ");
            request.getRequestDispatcher("/frontend/signInPage/signUpPage/signUp.jsp").forward(request, response);
            return;
        }

        // Continue with sign-up process if CAPTCHA is valid
        String token = null;
        String toEmail = request.getParameter("email");

        String subject = "Xác nhận email";
        token = UUID.randomUUID().toString();
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

    private boolean verifyCaptcha(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            return false;
        }

        try {
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;

            OutputStream out = conn.getOutputStream();
            out.write(postParams.getBytes());
            out.flush();
            out.close();

            Scanner in = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (in.hasNext()) {
                response.append(in.nextLine());
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            return json.getBoolean("success") && json.getDouble("score") >= 0.5;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}