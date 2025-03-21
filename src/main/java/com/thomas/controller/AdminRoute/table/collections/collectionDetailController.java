package com.thomas.controller.AdminRoute.table.collections;

import com.thomas.dao.model.Belts;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "collectionDetailController", value = "/admin/table/collections/details")
public class collectionDetailController extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String collectionId = request.getParameter("id");
//        List<Belts> beltsList = productService.findCollectionProduct(collectionId);
//
//        request.setAttribute("beltsList", beltsList);
//        request.getRequestDispatcher("/frontend/AdminPage/collectionView/collectionView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String message = request.getParameter("message");
//        if (message.equals("delete")) {
//            String orderItemId = request.getParameter("orderItemId");
//            int orderId = Integer.parseInt(request.getParameter("orderId"));
//            productService.deteleCollectionDetail(orderItemId);
//            if (request.getParameter("oneItem") != null) {
//                response.sendRedirect("/admin/table/collections");
//            } else {
//                response.sendRedirect("/admin/table/collections/details?=" + orderId);
//            }
//        }
    }
}

