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
import utils.ipAddress;

import java.io.IOException;

@WebServlet(name = "loginController", value = "/login")
public class loginController extends HttpServlet {
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
    }
}