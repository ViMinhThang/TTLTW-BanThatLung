package com.thomas.controller.AdminRoute.table.collections;

import com.thomas.dao.model.Collection;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "collectionAdminController", value = "/admin/table/collections")
public class collectionAdminController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Collection> collections = productService.getCollections();
//        request.setAttribute("collections", collections);
//        request.getRequestDispatcher("/frontend/AdminPage/allCollection/allCollection.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String message = request.getParameter("message");
//        if (message.equals("create")) {
//            String name = request.getParameter("collectionName");
//            productService.createCollection(name);
//        } else if (message.equals("delete")) {
//            int id = Integer.parseInt(request.getParameter("orderId"));
//            productService.deleteCollections(id);
//        }
//        response.sendRedirect("/admin/table/collections");
    }
}

