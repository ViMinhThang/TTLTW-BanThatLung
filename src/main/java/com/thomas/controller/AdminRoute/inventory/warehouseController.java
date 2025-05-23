package com.thomas.controller.AdminRoute.inventory;


import com.thomas.dao.model.Inventory;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "warehouseController", value = "/admin/inventory/inventories")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
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
        } else if (message.equals("import")) {
            purchaseService.deleteAllStock();
            Part filePart = request.getPart("file");
            if (filePart != null) {
                importCSV(filePart);
                response.sendRedirect("/admin/inventory/inventories");
                return;
            }
        }
        response.sendRedirect("/admin/inventory/inventories");

    }

    private void importCSV(Part filePart) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
            if (data.size() > 0) {
                saveToDatabase(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToDatabase(List<String[]> data) {
        data = data.subList(1, data.size() - 1)
                .stream()
                .map(arr -> Arrays.stream(arr)
                        .map(s -> s.replaceAll("\"", ""))
                        .toArray(String[]::new)).collect(Collectors.toList());
        for (String[] row : data) {
            Inventory inventory = new Inventory();
            inventory.setId(Integer.parseInt(row[0]));
            inventory.setBeltId(purchaseService.findBeltId(row[1]));
            inventory.setVariantId(purchaseService.getVariantId(row[1], row[3], row[2]));
            inventory.setStockQuantity(Integer.parseInt(row[4]));
            purchaseService.insertOne(inventory);
        }
    }
}