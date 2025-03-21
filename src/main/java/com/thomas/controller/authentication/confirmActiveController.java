package com.thomas.controller.authentication;

import com.thomas.services.TokenService;
import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "confirmActiveController", value = "/verify")
public class confirmActiveController extends HttpServlet {
    TokenService tokenService = new TokenService();
    UploadUserService uploadUserService = new UploadUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("messageRedirect");
        TokenService tokenService = new TokenService();
        String token = request.getParameter("token");
        if (token != null) {
            int userId = tokenService.verifyToken(token);
            boolean active = false;
            if (userId != 0) {
                active = tokenService.activeUser(userId);
            } else {
                uploadUserService.deleteUser(userId);
            }
            if (userId != 0 && active) {
                request.setAttribute("active", 1);
                request.setAttribute("messageRedirect", "Kích hoạt tài khoản thành công");
            } else {
                request.setAttribute("active", 1);
                request.setAttribute("messageRedirect", "Kích hoạt thất bại vui lòng đăng ký lại");
            }
            if (message != null) {
                request.setAttribute("messageRedirect", message);
                if (message.equals("orderDetailSuccess")) {
                    HttpSession session = request.getSession();
                    session.removeAttribute("cart");
                }
            }
        } else {
            request.setAttribute("active", 1);
            request.setAttribute("messageRedirect", message);
        }
        request.getRequestDispatcher("/frontend/signInPage/confirmSuccessSignUp/confirmSuccess.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

