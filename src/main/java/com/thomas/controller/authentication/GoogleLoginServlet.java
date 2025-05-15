package com.thomas.controller.authentication;

import com.thomas.dao.UserDao;
import com.thomas.dao.model.User;
import com.thomas.services.AuthService;
import com.thomas.services.UsesUsageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login-google")
public class GoogleLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            response.sendRedirect("/login");
            return;
        }

        try {
            // Lấy access token từ Google
            String accessToken = GoogleLoginController.getToken(code);
            // Lấy thông tin user từ Google
            User googleUser = GoogleLoginController.getUserInfo(accessToken);

            // Thiết lập thông tin OAuth
            googleUser.setOauthProvider("google");
            googleUser.setOauthId(googleUser.getOauthId()); // Giả định oauthId đã được set từ Google

            UserDao userDao = new UserDao();
            AuthService authService = new AuthService();
            UsesUsageService usageService = new UsesUsageService();

            // Tìm user trong DB qua OAuth
            User existingUser = userDao.findUserByOAuth(googleUser.getOauthId(), "google");
            if (existingUser == null) {
                // Tạo user mới
                googleUser.setPassword(null);
                googleUser.setIsActive(1);
                googleUser.setIsDeleted(0);
                userDao.registerUser(googleUser);
                existingUser = userDao.findUserByOAuth(googleUser.getOauthId(), "google");
            }

            if (existingUser != null && existingUser.getIsActive() == 1 && existingUser.getIsDeleted() == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("auth", existingUser);
                authService.saveSession(existingUser.getId(), session.getId());
                usageService.trackUserActivity(existingUser.getId(), request.getRemoteAddr());
                session.setAttribute("auth", existingUser);
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/login?error=google_failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login?error=google_error");
        }
    }
}