package com.thomas.controller.userInfoRoutes;

import com.thomas.dao.model.Address;
import com.thomas.services.UploadAddressService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addressController", value = "/address")
public class addressController extends HttpServlet {
    UploadAddressService uploadAddressService = new UploadAddressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
    }
}

