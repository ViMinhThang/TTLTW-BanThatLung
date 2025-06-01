package com.thomas.controller.AdminRoute.inventory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.thomas.dao.model.Supplier;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.LocalDateTimeAdapter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "SupplierController", value = "/admin/inventory/suppliers")
public class SupplierController extends HttpServlet {
    SupplierService supplierService = new SupplierService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("message") != null) {
            String message = request.getParameter("message");
            if (message.equals("takeSupplier")) {
                int supplierId = Integer.parseInt(request.getParameter("supplierId"));
                Supplier suppliers = supplierService.getSuppliers(supplierId).get(0);
                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
                String json = gson.toJson(suppliers);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                return;
            }
        }
        List<Supplier> suppliers = supplierService.getSuppliers(null);


        request.setAttribute("suppliers", suppliers);
        request.getRequestDispatcher("/frontend/AdminPage/inventory/allSuppliers/allSuppliers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("create")) {
            String name = request.getParameter("name");
            String contactInfo = request.getParameter("contactInfo");
            String createdDateStr = request.getParameter("createdDate");
            LocalDateTime updatedAt = LocalDateTime.now();
            LocalDateTime createdAt = null;
            if (createdDateStr != null && createdDateStr.length() == 10) {
                LocalDate date = LocalDate.parse(createdDateStr);
                createdAt = date.atStartOfDay();
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                createdAt = LocalDateTime.parse(createdDateStr, formatter);
            }
            Supplier supplier = new Supplier(name, contactInfo, createdAt, updatedAt);
            boolean updated = supplierService.createSupplier(supplier);
            if (updated) {
                response.sendRedirect("/admin/inventory/suppliers");
            }
        } else if (message.equals("update")) {
            String name = request.getParameter("name");
            String contactInfo = request.getParameter("contactInfo");
            String createdDateStr = request.getParameter("createdDate");
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            LocalDateTime updatedAt = LocalDateTime.now();
            LocalDateTime createdAt = null;
            if (createdDateStr != null && createdDateStr.length() == 10) {
                LocalDate date = LocalDate.parse(createdDateStr);
                createdAt = date.atStartOfDay();
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                createdAt = LocalDateTime.parse(createdDateStr, formatter);
            }
            Supplier supplier = new Supplier(name, contactInfo, createdAt, updatedAt);
            supplier.setId(supplierId);
            boolean updated = supplierService.updateSupplier(supplier);
            if (updated) {
                response.sendRedirect("/admin/inventory/suppliers");
            }
        } else if (message.equals("delete")) {
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            Supplier supplier = new Supplier();
            supplier.setId(supplierId);
            boolean updated = supplierService.deleteSupplier(supplier);
            if (updated) {
                response.sendRedirect("/admin/inventory/suppliers");
            }
        }
    }
}