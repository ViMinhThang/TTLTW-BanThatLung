package com.thomas.controller.AdminRoute.inventory;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.thomas.dao.model.Purchases;
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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "PurchasesController", value = "/admin/inventory/purchases")
public class PurchasesController extends HttpServlet {
    PurchaseService purchaseService = new PurchaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("message") != null) {
            int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
            Purchases purchase = purchaseService.getPurchases(purchaseId).get(0);
            purchaseService.setsupplierName(purchase);
            purchaseService.setBeltName(purchase);
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            String json = gson.toJson(purchase);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        List<Purchases> purchasesList = purchaseService.getPurchases(null);
        for (Purchases purchases : purchasesList) {
            purchaseService.setsupplierName(purchases);
            purchaseService.setBeltName(purchases);
        }
        request.setAttribute("purchasesList", purchasesList);
        request.getRequestDispatcher("/frontend/AdminPage/inventory/allPurchases/allPurchases.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("create")) {
            String name = request.getParameter("name").trim();
            String productName = request.getParameter("productName").trim();
            String[] parts = productName.split(" ");
            String beltName = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 2));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            LocalDateTime purchaseDate = LocalDate.parse(request.getParameter("purchaseDate"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            int beltId = purchaseService.findBeltId(beltName);
            int variantId = purchaseService.getVariantId(beltName, parts[parts.length - 1], parts[parts.length - 2]);
            int supplierId = purchaseService.getSupplierId(name);
            LocalDateTime createdAt = LocalDate.parse(request.getParameter("createdDate"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            Purchases purchases = new Purchases(supplierId, variantId, quantity, purchaseDate, createdAt, beltId);
            purchases.setSupplierName(name);
            boolean created = purchaseService.createPurchase(purchases) && purchaseService.addInventory(beltId, variantId, quantity);
            if (created) {
                response.sendRedirect("/admin/inventory/purchases");
            }
        } else if (message.equals("delete")) {
            Purchases purchases = new Purchases();
            purchases.setId(Integer.parseInt(request.getParameter("purchaseId")));
            boolean deleted = purchaseService.deletePurchase(purchases);
            if (deleted) {
                response.sendRedirect("/admin/inventory/purchases");
            }
        } else if (message.equals("update")) {
            String name = request.getParameter("name").trim();
            String productName = request.getParameter("productName").trim();
            String[] parts = productName.split(" ");
            String beltName = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 2));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            LocalDateTime purchaseDate = LocalDate.parse(request.getParameter("purchaseDate"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            int beltId = purchaseService.findBeltId(beltName);
            int variantId = purchaseService.getVariantId(beltName, parts[parts.length - 1], parts[parts.length - 2]);
            int supplierId = purchaseService.getSupplierId(name);
            LocalDateTime createdAt = LocalDate.parse(request.getParameter("createdDate"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            Purchases purchases = new Purchases(supplierId, variantId, quantity, purchaseDate, createdAt, beltId);
            purchases.setId(Integer.parseInt(request.getParameter("purchaseId")));
            boolean updated = purchaseService.updatePurchase(purchases) && purchaseService.addInventory(beltId, variantId, quantity);
            if (updated) {
                response.sendRedirect("/admin/inventory/purchases");
            }
        }
    }
}