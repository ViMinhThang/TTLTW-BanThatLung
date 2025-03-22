package com.thomas.controller.AdminRoute.table.belts;

import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "productDescController", value = "/admin/table/belts/createProductDescription")
public class productDescController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/frontend/AdminPage/updateProductDescription/updateProductDesc.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("beltId"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        String description = request.getParameter("desc");
        productService.saveDesc(id, description, variantId);
        response.sendRedirect("/admin/table/belts");
    }
}

