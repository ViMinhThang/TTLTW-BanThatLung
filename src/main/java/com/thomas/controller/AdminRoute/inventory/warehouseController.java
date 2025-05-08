package com.thomas.controller.AdminRoute.inventory;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thomas.dao.model.Inventory;
import com.thomas.dao.model.Purchases;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.LocalDateTimeAdapter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

    }
}