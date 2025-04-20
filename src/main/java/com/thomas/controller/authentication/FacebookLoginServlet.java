package com.thomas.controller.authentication;
import com.thomas.dao.model.User;
import com.thomas.dao.UserDao;
import com.thomas.services.AuthService;
import com.thomas.services.UsesUsageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login-facebook")
public class FacebookLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null) {
            response.sendRedirect("/login?error=facebook_failed");
            return;
        }

        try {
            String accessToken = FacebookLoginController.getToken(code);
            User facebookUser = FacebookLoginController.getUserInfo(accessToken);

            UserDao userDao = new UserDao();
            AuthService authService = new AuthService();
            UsesUsageService usageService = new UsesUsageService();

            // Kiểm tra email đã tồn tại
            User existingLocalUser = userDao.findUserEmail(facebookUser.getEmail(), null);
            if (existingLocalUser != null && existingLocalUser.getOauthProvider() == null) {
                response.sendRedirect("/login?error=email_exists");
                return;
            }

            // Tìm hoặc tạo user
            User existingUser = userDao.findUserByOAuth(facebookUser.getOauthId(), "facebook");
            if (existingUser == null) {
                facebookUser.setIsActive(1);
                facebookUser.setIsDeleted(0);
                userDao.registerUser(facebookUser);
                existingUser = userDao.findUserByOAuth(facebookUser.getOauthId(), "facebook");
            }

            // Đăng nhập
            HttpSession session = request.getSession();
            authService.saveSession(existingUser.getId(), session.getId());
            usageService.trackUserActivity(existingUser.getId(), request.getRemoteAddr());
            session.setAttribute("auth", existingUser);
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login?error=facebook_error");
        }
    }
}