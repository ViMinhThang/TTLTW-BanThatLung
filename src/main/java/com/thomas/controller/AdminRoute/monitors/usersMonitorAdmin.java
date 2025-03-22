package com.thomas.controller.AdminRoute.monitors;

import com.thomas.dao.model.UsersUsage;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

@WebServlet(name = "usersMonitorAdmin", value = "/admin/monitors/users")

public class usersMonitorAdmin extends HttpServlet {

    UsesUsageService usesUsageService = new UsesUsageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UsersUsage> usersUsages = usesUsageService.getAllUsersUsage();
        request.setAttribute("usersUsages", usersUsages);
        request.getRequestDispatcher("/frontend/AdminPage/Activity/users/usersMonitor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
