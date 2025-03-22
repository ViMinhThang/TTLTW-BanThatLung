package com.thomas.controller.userInfoRoutes;

import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "userPrivacyController", value = "/userPrivacy")
public class userPrivacyController extends HttpServlet {
    UploadUserService uploadUserService = new UploadUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/frontend/userInfoPage/privacy/privacy.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (uploadUserService.softDeleteUser(userId)) {
            HttpSession session = request.getSession();
            session.removeAttribute("auth");
            response.sendRedirect("/login");
        }
    }
}

