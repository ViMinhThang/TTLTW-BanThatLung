package com.thomas.controller.authentication;

import com.thomas.dao.model.User;
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        TokenService tokenService = new TokenService();
        String token = request.getParameter("token");
        if (token != null) {
            int userId = tokenService.verifyToken(token);
            boolean active = false;
            if (userId != 0) {
                active = tokenService.activeUser(userId);
            } else {
                uploadUserService.deleteUser(userId, user.getId());
            }
            if (userId != 0 && active) {
                request.setAttribute("active", 1);
                request.setAttribute("messageRedirect", "Kích ho?t tài kho?n thành công");
            } else {
                request.setAttribute("active", 1);
                request.setAttribute("messageRedirect", "Kích ho?t th?t b?i vui lòng ??ng ký l?i");
            }
            if (message != null) {
                request.setAttribute("messageRedirect", message);
                if (message.equals("orderDetailSuccess")) {
                    HttpSession session1 = request.getSession();
                    session1.removeAttribute("cart");
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

