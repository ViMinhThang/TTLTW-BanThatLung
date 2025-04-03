package com.thomas.controller.authentication;

import com.thomas.dao.model.User;
import com.thomas.services.AuthService;
import com.thomas.services.UsesUsageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import utils.ipAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet(name = "loginController", value = "/login")
public class loginController extends HttpServlet {
    private static final String SECRET_KEY = "6Le5fgQrAAAAAMTV1TBfbcU5S10d7Eb8yGL_JSRD";
    UsesUsageService usesUsageService = new UsesUsageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("auth") == null) {
            request.getRequestDispatcher("/frontend/signInPage/signInPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        boolean isCaptchaValid = verifyCaptcha(gRecaptchaResponse);

        if (isCaptchaValid) {
            AuthService service = new AuthService();
            User user = service.checkLogin(userEmail, password);
            if (user != null) {
                HttpSession session = request.getSession();
                service.saveSession(user.getId(), session.getId());
                usesUsageService.trackUserActivity(userEmail, ipAddress.getClientIp(request));
                session.setAttribute("auth", user);
                response.sendRedirect("/");
            } else {
                request.setAttribute("errorMessage", "Sai mật khẩu hoặc tài khoản");
                request.getRequestDispatcher("/frontend/signInPage/signInPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "CAPTCHA không hợp lệ");
            request.getRequestDispatcher("/frontend/signInPage/signInPage.jsp").forward(request, response);
        }
    }

    private boolean verifyCaptcha(String gRecaptchaResponse) {
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
            return json.getBoolean("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}