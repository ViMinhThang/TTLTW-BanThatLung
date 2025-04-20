package com.thomas.controller.authentication;

import com.thomas.services.MD5Service;
import com.thomas.services.TokenService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "resetPasswordController", value = "/resetPassword")
public class resetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenService tokenService = new TokenService();
        String token = request.getParameter("token");
        if (token == null || token.isEmpty()) {
            response.getWriter().println("Thiếu token hoặc không hợp lệ.");
            return;
        }
        int userId = tokenService.verifyToken(token);
        if (userId != 0) {
            response.sendRedirect("/frontend/signInPage/resetPasswordPage/resetPasswordPage.jsp?token=" + token);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String token = request.getParameter("token");
        if (!password.equals(retypePassword)) {
            response.getWriter().println("Mật khẩu không khớp.");
        }
        TokenService tokenService = new TokenService();
        if (tokenService.updatePasswordByToken(token, MD5Service.hashPassword(password))) {
            response.sendRedirect("/");
        }
    }
}

