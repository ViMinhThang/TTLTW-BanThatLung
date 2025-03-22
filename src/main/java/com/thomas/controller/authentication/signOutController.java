package com.thomas.controller.authentication;

import com.thomas.dao.model.User;
import com.thomas.services.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "signOutController", value = "/logout")
public class signOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService authService = new AuthService();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("auth");
        authService.deleteSession(u.getId());
        session.removeAttribute("auth");
        response.sendRedirect("/login");
    }
}