package com.thomas.controller.AdminRoute.inventory;


import com.thomas.dao.model.Inventory;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;

@WebServlet(name = "warehouseController", value = "/admin/inventory/inventories")
public class warehouseController extends HttpServlet {
    PurchaseService purchaseService = new PurchaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Inventory> inventoryList = purchaseService.getInventory(null, null);

        for (Inventory inventory : inventoryList) {
            purchaseService.setInventoryNames(inventory);
        }
        request.setAttribute("inventoryList", inventoryList);
        request.getRequestDispatcher("/frontend/AdminPage/inventory/allInventory/allInventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("updateQuantity")) {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String color = request.getParameter("coloSelect");
            String size = request.getParameter("sizeSelect");
            purchaseService.updateInventory(name, quantity, color, size);
        }
        response.sendRedirect("/admin/inventory/inventories");

    }
}